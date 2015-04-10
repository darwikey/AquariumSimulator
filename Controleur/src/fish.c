#include "fish.h"
#include <stdlib.h>
#include <unistd.h>


struct fish* fish__create_fish(char* name){
  struct fish* f = malloc(sizeof(struct fish));
  f->name = name;
  
  f->target_x = rand() % 100;
  f->target_y = rand() % 100;
  
  f->size_x = rand() % 7 + 4;
  f->size_y = rand() % 7 + 4;

  f->delay = rand() % 5 + 1;

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


void fish__update(struct aquarium* a){

  while(1){
    for (int i = 0; i < a->fish_number; i++){
      a->fishs[i]->delay--;

      if (a->fishs[i]->delay <= 0){
	 a->fishs[i]->target_x = rand() % 100;
	 a->fishs[i]->target_y = rand() % 100;

	 a->fishs[i]->delay = rand() % 5 + 1;
      }
    }

    sleep(1);
  }
}
