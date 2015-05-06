#ifndef GRAPH_H
#define GRAPH_H
#include <stdio.h>

struct graph;

typedef struct Agnode_s Agnode_t;
typedef Agnode_t node;

int  graph__load(char* path, struct graph**);
void graph__show(struct graph*, FILE* std_out);

/**
 * @return 1 if errors
 */
int  graph__add_link(struct graph*,char* node1, char* node2);
int  graph__del_link(struct graph*,char* node1, char* node2);
int  graph__remove_node(struct graph*,char* node);
int  graph__save(struct graph*, char* path);
void graph__free(struct graph **);

void graph__node_connect(struct graph *g, node* node);
void graph__node_disconnect(struct graph *g, node* node);

/**
 * get a name of an unconnected node in the graph.
 * @param prefered_name if not NULL, the return name try to match this one.
 * @return null if all nodes are already connected
 */
node* graph__get_not_connected_node(struct graph*g,char *prefered_name);
char* graph__get_node_name(struct graph*g, node* node);


#endif
