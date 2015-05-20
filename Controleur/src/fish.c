#include "fish.h"
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include "utils.h"


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
  f->is_started = 0;

  f->node = NULL;

  f->path_way = UNDEFINED_PATH_WAY;

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


int fish__start_fish(struct aquarium* a, char* fish_name){
  for (int i = 0; i < a->fish_number; i++){
    if (strcmp(a->fishs[i]->name, fish_name) == 0){
      a->fishs[i]->is_started = 1;
      return 1;
    }
  }
  return 0;
}


int fish__remove_fish(struct aquarium* a, char* fish_name){
  for (int i = 0; i < a->fish_number; i++){
    if (strcmp(a->fishs[i]->name, fish_name) == 0){
      free(a->fishs[i]->name);
      free(a->fishs[i]);

      for (int j = i; j < a->fish_number - 1; j++){
	a->fishs[i] = a->fishs[i+1];
      }

      a->fish_number--;
      return 1;
    }
  }
  return 0;
}


void fish__status(struct aquarium* a, char* buffer, int buffer_length){
    int used_char = snprintf(buffer, buffer_length, "%d poissons trouvés",a->fish_number);
    buffer += used_char;
    buffer_length -= used_char;
  for (int i = 0; i < a->fish_number; i++){
    struct fish* f = a->fishs[i];
    int n = snprintf(buffer, buffer_length, "\nfish %s at %dx%d, %dx%d", f->name, f->target_x, f->target_y, f->size_x, f->size_y);

    if (n > 0 && n < buffer_length){
      buffer += n;
      buffer_length -= n;
    }
    else{
      return;
    }
  }

  if (buffer_length > 2){
    buffer[0] = '\n';
    buffer[1] = '\0';
  }
}


void fish__get_fishes(struct aquarium* a, node* graphe_node, char* buffer, int buffer_length){

  int n = snprintf(buffer, buffer_length, "list");
  buffer += n;
  buffer_length -= n;

  for (int i = 0; i < a->fish_number; i++){
    struct fish* f = a->fishs[i];

    // on ne renvoie que les poissons appartenants au node affecté au client
    if (f->node != graphe_node){
      continue;
    }

    int n = snprintf(buffer, buffer_length, " [%s at %dx%d,%dx%d,%d]", f->name, f->target_x, f->target_y, f->size_x, f->size_y, f->delay);

    if (n > 0 && n < buffer_length){
      buffer += n;
      buffer_length -= n;
    }
    else{
      return;
    }
  }

  if (buffer_length > 2){
    buffer[0] = '\n';
    buffer[1] = '\0';
  }
}


void fish__update(struct aquarium* a){

  while(1){
    for (int i = 0; i < a->fish_number; i++){
        struct fish* current_fish = a->fishs[i];
      
      if (!current_fish->is_started){
	continue;
      }
      
      current_fish->delay--;

      if (current_fish->delay <= 0){
          int must_update_pos = 1;

	// si sa destination est en dehors de l'ecran, on le change de node
          enum Direction dir = 0;

          if (current_fish->target_x < - current_fish->size_x)
              dir |= LEFT;
          if (current_fish->target_x > 100)
              dir |= RIGHT;
          if (current_fish->target_y < - current_fish->size_y)
              dir |= UP;
          if (current_fish->target_y > 100)
              dir |= DOWN;
          if (dir & (UP | DOWN | RIGHT | LEFT)){
              if (a->graph != NULL && current_fish->node != NULL){
                  node* next_node = graph__get_random_connected_neighbour(a->graph, current_fish->node, dir);

                  log(LOG_INFO, "node : %p,  next node : %p\n", current_fish->node, next_node);

                  if (next_node != NULL){
                      current_fish->node = next_node;
                      must_update_pos = 0;

                      if ((dir & RIGHT) && 
                              (current_fish->path_way == HORIZONTAL_PATH_WAY || 
                               current_fish->path_way == RANDOM_PATH_WAY)){
                          current_fish->target_x = 1 - current_fish->size_x;
                      }

                      if ((dir & LEFT) && 
                              (current_fish->path_way == HORIZONTAL_PATH_WAY || 
                               current_fish->path_way == RANDOM_PATH_WAY)){
                          current_fish->target_x = 99;
                      }

                      if ((dir & UP) && 
                              (current_fish->path_way == VERTICAL_PATH_WAY || 
                               current_fish->path_way == RANDOM_PATH_WAY)){
                          current_fish->target_y = 99;
                      }

                      if ((dir & DOWN) && 
                              (current_fish->path_way == VERTICAL_PATH_WAY || 
                               current_fish->path_way == RANDOM_PATH_WAY)){
                          current_fish->target_y = 1 - current_fish->size_y;
                      }

                      current_fish->delay = 2;

                  }
              }
          }

          if (must_update_pos){
              switch(current_fish->path_way){
                  default:
                  case RANDOM_PATH_WAY:
                      current_fish->target_x = (rand() % 175) - 50;
                      current_fish->target_y = (rand() % 175) - 50;
                      break;

                  case HORIZONTAL_PATH_WAY:
                      current_fish->target_x = 120;
                      current_fish->target_y = 0;
                      break;

                  case VERTICAL_PATH_WAY:
                      current_fish->target_x = 0;
                      current_fish->target_y = 120;
                      break;
              }

              current_fish->delay = rand() % 4 + 4;
              if (current_fish->target_x > 100)
                  current_fish->target_x = 101;
              if (current_fish->target_x < -25 || current_fish->target_x < - current_fish->size_x)
                  current_fish->target_x = -1 - current_fish->size_x;
              if (current_fish->target_y > 100)
                  current_fish->target_y = 101;
              if (current_fish->target_y < -25 || current_fish->target_y < - current_fish->size_y)
                  current_fish->target_y = -1 - current_fish->size_y;
          }
	
	log(LOG_INFO, "nouvelle destination pour %s : %dx%d, (en %d seconde)\n", current_fish->name, current_fish->target_x, current_fish->target_y, current_fish->delay);
      }
    }

    sleep(1);
  }
}
