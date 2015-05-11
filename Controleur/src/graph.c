#include "graph.h"
#include <graphviz/cgraph.h>
//#include <graphviz/ingraphs.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

struct graph{
    Agraph_t *agraph;
    Agsym_t *connect_attribute;
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
    (*g)->connect_attribute = agattr((*g)->agraph, AGNODE, "connected", "false");
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


static int graph__node_is_connected(struct graph*g, Agnode_t* node){
    return strcmp(agxget(node,g->connect_attribute),"false");
}

void graph__node_connect(struct graph* g, node* node){
    assert(node != NULL);
    assert(!graph__node_is_connected(g,node));
    agxset(node, g->connect_attribute ,"true");
}

void graph__node_disconnect(struct graph* g, node* node){
    assert(node != NULL);
    assert(graph__node_is_connected(g,node));
    agxset(node, g->connect_attribute, "false");
}

node* graph__get_not_connected_node(struct graph*g, char* prefered_name){
    Agnode_t* node = NULL;
    if (prefered_name){
        for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
            if (!strcmp(agget(n,"label"),prefered_name) && !graph__node_is_connected(g,n))
                node = n;
        }
    }
    if (node == NULL){
        for (Agnode_t *n = agfstnode(g->agraph); n; n = agnxtnode(g->agraph,n)){
            if (!graph__node_is_connected(g,n))
                node = n;
        }
    }
    return node;
}

char* graph__get_node_name(struct graph* g, node* node){
    if (node){
        return agget(node,"label");
    }
    return NULL;
}

node* graph__get_random_connected_neighbour(struct graph* g, node* node){
    int degree = agdegree(g->agraph, node, 0, 1);
    if (!degree){
        return NULL;
    }
    int neighbour_pos = rand() % degree;
    Agnode_t *connected_neighbour = NULL;
    for (Agedge_t *e = agfstout(g->agraph, node); e; e = agnxtout(g->agraph, e)){
        Agnode_t *current_neighbour = agtail(e);
        if (graph__node_is_connected(g,current_neighbour)){
            connected_neighbour = current_neighbour;
        }
        if (neighbour_pos<=0 && connected_neighbour){
            return connected_neighbour;

        }
        neighbour_pos --;
    }
    return connected_neighbour;
}

