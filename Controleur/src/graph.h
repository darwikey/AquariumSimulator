#ifndef GRAPH_H
#define GRAPH_H
#include <stdio.h>

struct graph;

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

#endif
