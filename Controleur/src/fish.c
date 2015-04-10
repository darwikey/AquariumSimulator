#include "fish.h"
#include "stdlib.h"


struct fish* fish__create_fish(char* name, float x, float y){
  struct fish* f = malloc(sizeof(struct fish));
  f->name = name;
  f->x = x;
  f->y = y;
  f->vx = (rand() / (float)RAND_MAX) * 2.f - 1.f;
  f->vy = (rand() / (float)RAND_MAX) * 2.f - 1.f;

  return f;
}


void fish__init_aquarium(struct aquarium* a){
  a->fishs = NULL;
  a->fish_number = 0;
  a->graph = NULL;
}


void fish__add_fish(struct aquarium* a, struct fish* f){
  a->fish_number++;

  if (a->fishs == NULL){
    a->fishs = malloc(a->fish_number * sizeof(struct fish*));
  }
  else{
    a->fishs = realloc(a->fishs, a->fish_number * sizeof(struct fish*));
  }

  a->fishs[a->fish_number - 1] = f;
}
