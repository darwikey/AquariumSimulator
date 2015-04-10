#include "graph.h"
#include <graphviz/cgraph.h>
//#include <graphviz/ingraphs.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

struct graph{
    Agraph_t *agraph;
};

int graph__load(char* path, struct graph **g){
    assert(g!=NULL);
    if(*g!=NULL){
        graph__free(g);
    }
    *g = malloc(sizeof(**g));
    FILE* f =NULL;
    f = fopen(path,"r");
    if (f == NULL){
        fprintf(stderr,"unable to open \"%s\"\n", path);
        free(*g);
        *g = NULL;
        return 1;
    }
    (*g)->agraph = agread(f,0);
    if ((*g)->agraph == NULL){
        fprintf(stderr,"unable to read a graph in file \"%s\"\n", path);
        free(*g);
        *g = NULL;
        return 1;
    }
    fclose(f);
    printf("\t-> %s loaded (%d display view)!\n",agnameof((*g)->agraph),agnnodes((*g)->agraph));
    return 0;
}

void graph__show(struct graph *g, FILE* out){
    if (g == NULL){
        return;
    }

    for (Agnode_t *v = agfstnode(g->agraph); v; v = agnxtnode(g->agraph,v)){
        fprintf(out,"\t%s [label=\"%s\"];\n",agnameof(v), agget(v,"label"));
    }
    for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
        for (Agedge_t *e = agfstedge(n->root, n); e; e = agnxtedge(n->root, e, n)){
            char * node1_name = agnameof(n);
            char * node2_name = agnameof(e->node);
            if (strcmp(node1_name, node2_name) > 0)
                break;
            fprintf(out,"\t%s -- %s [label=\"%s\"];\n",node1_name,node2_name, agget(e,"label"));
        }
    }
}

int graph__add_link(struct graph *g, char *node1_name, char *node2_name){
    Agnode_t *node1=NULL, *node2=NULL;
    for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
        if (!strcmp(agget(n,"label"),node1_name))
            node1 = n;
        if (!strcmp(agget(n,"label"),node2_name))
            node2 = n;
    }
    if (node1 == NULL || node2 == NULL){
        fprintf(stderr,"node %s unfound\n",(node1) ? node2_name : node1_name);
        return 1;
    }
    agedge(g->agraph,node1,node2,NULL,1);
    printf("\t-> Link created between %s and %s\n", node1_name, node2_name);
    return 0;
}

int graph__del_link(struct graph* g, char* node1_name, char* node2_name){
    Agnode_t *node1=NULL, *node2=NULL;
    for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
        if (!strcmp(agget(n,"label"),node1_name))
            node1 = n;
        if (!strcmp(agget(n,"label"),node2_name))
            node2 = n;
    }
    if (node1 == NULL || node2 == NULL){
        fprintf(stderr,"node %s unfound\n",(node1) ? node2_name : node1_name);
        return 1;
    }
    Agedge_t *e =agedge(g->agraph,node1,node2,NULL,0);

    if (e == NULL){
        fprintf(stderr,"no link found between %s and %s\n", node2_name, node1_name);
        return 1;
    }
    agdeledge(g->agraph,e);
    printf("\t-> Link deleted between %s and %s.\n", node1_name, node2_name);
    return 0;
}

int graph__remove_node(struct graph* g, char* name){
    Agnode_t *node;
    for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
        if (!strcmp(agget(n,"label"),name))
            node = n;
    }
    if (node == NULL){
        fprintf(stderr,"node %s unfound\n",name);
        return 1;
    }
    agdelnode(g->agraph,node);
    printf("\t-> View %s deleted!\n", name);
    return 0;
}

int graph__save(struct graph* g, char * path){
    assert(g != NULL);
    FILE* f =NULL;
    f = fopen(path,"w");
    if (f == NULL){
        fprintf(stderr,"unable to open \"%s\"\n", path);
        return 1;
    }
    agwrite(g->agraph,f);
    return 0;
}

void graph__free(struct graph**g){
    agclose((*g)->agraph);
    free(*g);
    *g=NULL;
}
