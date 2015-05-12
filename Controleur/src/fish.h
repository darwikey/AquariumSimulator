#ifndef FISH_H
#define FISH_H
#include "graph.h"

enum path_way{
  UNDEFINED_PATH_WAY, RANDOM_PATH_WAY, HORIZONTAL_PATH_WAY, VERTICAL_PATH_WAY
};

struct fish{
  int target_x, target_y; //position a atteindre
  int size_x, size_y; //taille du poisson
  int delay; // delai en seconde avant update
  char* name;
  int is_started;
  node* node;
  enum path_way path_way;
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

// start the movement of a fish
int fish__start_fish(struct aquarium* a, char* fish_name);

// supprime un poisson de l'aquarium, retourne 1 en cas de succés
int fish__remove_fish(struct aquarium* a, char* fish_name);

// rempli un buffer avec la liste des poissons dans l'aquarium
void fish__status(struct aquarium* a, char* buffer, int buffer_length);

//rempli un buffer avec la liste des poissons dans l'aquarium
void fish__get_fishes(struct aquarium* a, node* graphe_node, char* buffer, int buffer_length);

// met a jour la position des poissons
void fish__update(struct aquarium* a);

#endif
