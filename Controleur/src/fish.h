#ifndef FISH_H
#define FISH_H
#include "graph.h"


struct fish{
  int target_x, target_y; //position a atteindre
  int size_x, size_y; //taille du poisson
  int delay; // delai en seconde avant update
  char* name;
};

struct aquarium{
  struct fish** fishs;
  unsigned int fish_number;
  struct graph *graph;
};

// create a fish with a random speed
struct fish* fish__create_fish(char* name);

//init aquarium
void fish__init_aquarium(struct aquarium* a);

//add a fish in the aquarium
void fish__add_fish(struct aquarium* a, struct fish* f);

// met a jour la position des poissons
void fish__update(struct aquarium* a);

#endif
