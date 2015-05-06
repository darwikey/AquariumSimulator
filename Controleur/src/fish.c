#include "fish.h"
#include <stdlib.h>
#include <unistd.h>
#include <string.h>


struct fish* fish__create_fish(char* name){
  struct fish* f = malloc(sizeof(struct fish));
  
  int n = strlen(name) + 1;
  f->name = malloc(n);
  if(f->name) {
    strcpy(f->name, name);
  }
   
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


int fish__remove_fish(struct aquarium* a, char* fish_name){
  for (int i = 0; i < a->fish_number; i++){
    if (a->fishs[i]->name){
      free(a->fishs[i]);

      for (int j = i; j < a->fish_number - 1; j++){
	a->fishs[i] = a->fishs[i+1];
      }
      return 1;
    }
  }
  return 0;
}


void fish__status(struct aquarium* a, char* buffer, int buffer_length){
  for (int i = 0; i < a->fish_number; i++){
    struct fish* f = a->fishs[i];
    int n = snprintf(buffer, buffer_length, "fish %s at %dx%d, %dx%d", f->name, f->target_x, f->target_y, f->size_x, f->size_y);

    if (n > 0 && n < buffer_length){
      buffer += n;
      buffer_length -= n;
    }
    else{
      return;
    }
  }

  if (buffer_length > 1){
    buffer[0] = '\n';
  }
}


void fish__getFishes(struct aquarium* a, char* buffer, int buffer_length){

  int n = snprintf(buffer, buffer_length, "fish");
  buffer += n;
  buffer_length -= n;

  for (int i = 0; i < a->fish_number; i++){
    struct fish* f = a->fishs[i];
    int n = snprintf(buffer, buffer_length, " [%s at %dx%d,%dx%d,%d]", f->name, f->target_x, f->target_y, f->size_x, f->size_y, f->delay);

    if (n > 0 && n < buffer_length){
      buffer += n;
      buffer_length -= n;
    }
    else{
      return;
    }
  }

  if (buffer_length > 1){
    buffer[0] = '\n';
  }
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
