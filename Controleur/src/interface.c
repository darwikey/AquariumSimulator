#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include "interface.h"
#include "graph.h"
#include "fish.h"
#include "network.h"


#define ARGUMENT_SIZE 10
#define BUFFER_SIZE 1024

// fonctions internes
char** parse_command(char* buffer);
char* parse_display_msg(char** arguments, struct aquarium*, struct display*);
char* parse_user_msg(char** arguments, struct aquarium*);
void* task_user_input(void* aquarium);


//copie une chaine de caractere
char* str_copy (const char *s) {
  char *d = malloc (strlen (s) + 1);
  if (d == NULL) 
    return NULL;
  strcpy (d,s);
  return d;
}


void interface__wait_user_input(struct aquarium* aquarium)
{
  static pthread_t thread;
  pthread_create(&thread, NULL, task_user_input, aquarium);
}


void* task_user_input(void* aquarium){
  char buffer[BUFFER_SIZE] = {0};

  while(1)
    {
      printf("$ ");
      fgets(buffer, BUFFER_SIZE, stdin);

      char** arguments = parse_command(buffer);
      char* msg = parse_user_msg(arguments, (struct aquarium*)aquarium);

      printf("%s", msg);

      free(msg);
    }
  
}


char** parse_command(char* buffer)
{
  char* pos = strrchr(buffer, '\n');
  if (pos != NULL)
    {
      *pos = '\0';
    }

  // tableau des arguments
  char** arguments = malloc(ARGUMENT_SIZE * sizeof(char *));
  for (int i = 0; i < ARGUMENT_SIZE; i++)
    {
      arguments[i] = NULL;
    }

  // découpe la chaine à chaque espace
  char* p = strtok(buffer, " ");
  int i = 0;
  while (p != NULL)
    {
      if (i < ARGUMENT_SIZE)
	{
	  arguments[i] = malloc(1 + strlen(p));
	  strcpy(arguments[i], p);
	  i++;
	}
      else
	{
	  break;
	}

      // découpe la chaine à chaque espace
      p = strtok(NULL, " ");
    }

  return arguments;
}


void parse_coord(char* argument, int* coord_x, int* coord_y){
  *coord_x = atoi(argument);

  for (int i = 0; argument[i] != '\0'; i++){
    if (argument[i] == 'x'){
      *coord_y = atoi(argument + i + 1);
      return;
    }
  }
}


 enum path_way parse_path_way(char* argument){
  if (strcmp(argument, "RandomPathWay") == 0){
    return RANDOM_PATH_WAY;
  }
  else if(strcmp(argument, "HorizontalPathWay") == 0){
    return HORIZONTAL_PATH_WAY;
  }
  else if(strcmp(argument, "VerticalPathWay") == 0){
    return VERTICAL_PATH_WAY;
  }
  else{
    return UNDEFINED_PATH_WAY;
  }
}


char* parse_display_msg(char** arguments, struct aquarium *aquarium, struct display *display)
{
  char* buffer = malloc(BUFFER_SIZE);
  buffer[0] = '\0';

  if (arguments[0] == NULL)
    {
      snprintf(buffer, BUFFER_SIZE, "pas de commande\n");
    }
  else if (strcmp(arguments[0], "hello") == 0) 
    {
      char * name = NULL;
      if (arguments[1] != NULL && arguments[2] != NULL && arguments[3] != NULL && strcmp(arguments[1],"in") == 0 && strcmp(arguments[2],"as") == 0){
	name = arguments[3];
      }
      if (display->node){
          graph__node_disconnect(aquarium->graph, display->node);
      }
      display->node = graph__get_not_connected_node(aquarium->graph, name);
      name = graph__get_node_name(aquarium->graph, display->node);
      if(display->node){
          graph__node_connect(aquarium->graph, display->node);
      }
      if (name){
	snprintf(buffer,BUFFER_SIZE,"greeting %s\n",name);
      }
      else{
	snprintf(buffer,BUFFER_SIZE,"no greeting");
      }
      
    }
  
  else if (strcmp(arguments[0], "ping") == 0) 
    {
        if (arguments[1]){
            snprintf(buffer,BUFFER_SIZE,"pong %s\n",arguments[1]);
        }else{
            snprintf(buffer,BUFFER_SIZE,"pong\n");
        }
    }

  else if (strcmp(arguments[0], "status") == 0) 
    {
      fish__status(aquarium, buffer, BUFFER_SIZE);
    }

  else if (strcmp(arguments[0], "addFish") == 0) 
    {
      if (display->node == NULL){
	snprintf(buffer,BUFFER_SIZE,"NOK : vous n'etes pas connecté.\n");
      }
      else{
	if (arguments[1] && arguments[2] && arguments[3] && arguments[4] && arguments[5]){
	  struct fish* fish = fish__create_fish(str_copy(arguments[1]));
	  parse_coord(arguments[3], &fish->target_x, &fish->target_y);
	  parse_coord(arguments[4], &fish->size_x, &fish->size_y);
	  fish->node = display->node;
	  
	  //modele de mobilité
	  fish->path_way = parse_path_way(arguments[5]);
	  if (fish->path_way == UNDEFINED_PATH_WAY){
	    snprintf(buffer,BUFFER_SIZE,"NOK : modèle de mobilité inconnue\n");
	  }
	  else{
	    fish__add_fish(aquarium, fish);
	  
	    snprintf(buffer,BUFFER_SIZE,"OK\n");
	  }
	}
	else{
	  snprintf(buffer,BUFFER_SIZE,"NOK : nécessite 5 arguments\n");
	}
      }
    }

  else if (strcmp(arguments[0], "delFish") == 0)
    {
      if (display->node == NULL){
	snprintf(buffer,BUFFER_SIZE,"NOK : vous n'etes pas connecté.\n");
      }
      else{
	if (arguments[1]){
	  if (fish__remove_fish(aquarium, arguments[1])){
	    snprintf(buffer,BUFFER_SIZE,"OK\n");
	  }
	  else{
	    snprintf(buffer,BUFFER_SIZE,"NOK : poisson inexistant\n");
	  }
	}
	else{
	  snprintf(buffer,BUFFER_SIZE,"NOK : manque un argument\n");
	}
      }
    }

  else if (strcmp(arguments[0], "startFish") == 0)
    {
      if (display->node == NULL){
	snprintf(buffer,BUFFER_SIZE,"NOK : vous n'etes pas connecté.\n");
      }
      else{
	if (arguments[1]){
	  if (fish__start_fish(aquarium, arguments[1])){
	    snprintf(buffer,BUFFER_SIZE,"OK\n");
	  }
	  else{
	    snprintf(buffer,BUFFER_SIZE,"NOK : poisson inexistant\n");
	  }
	}
	else{
	  snprintf(buffer,BUFFER_SIZE,"NOK : manque un argument\n");
	}
      }
    }

  else if (strcmp(arguments[0], "getFishesContinuously") == 0)
    {
      if (display->node == NULL){
	snprintf(buffer,BUFFER_SIZE,"NOK : vous n'etes pas connecté.\n");
      }
      else{
	display->get_fish_continously = 1;
	snprintf(buffer, BUFFER_SIZE, "OK\n");
      }
    }

  else if (strcmp(arguments[0], "log") == 0 &&
          arguments[1] != NULL &&
          strcmp(arguments[1], "out") == 0)
  {
      display->log_out = 1;
      snprintf(buffer, BUFFER_SIZE, "bye\n");
  }

  else
    {
      snprintf(buffer, BUFFER_SIZE, "NOK : commande (%s) introuvable\n", arguments[0]);
    }


  for (int i = 0; arguments[i] != NULL; i++)
    {
      free(arguments[i]);
    }
  free(arguments);

  return buffer;
}


char* parse_user_msg(char** arguments, struct aquarium * aquarium)
{
  char* buffer = malloc(BUFFER_SIZE);
  buffer[0] = '\0';

  if (arguments[0] == NULL)
    {
      snprintf(buffer, BUFFER_SIZE, "pas de commande\n");
    }
  else if (strcmp(arguments[0], "load") == 0)
    {
      if (arguments[1] == NULL){
          snprintf(buffer,BUFFER_SIZE, "pas de fichier indiqué\n");
      }else{
          graph__load(arguments[1], &(aquarium->graph));
      }
    }
  else if (strcmp(arguments[0], "show") == 0)
    {
      graph__show(aquarium->graph,stdout);
    }
  else if (strcmp(arguments[0], "add") == 0)
    {
      if (arguments[1] == NULL || arguments[2] == NULL){
          snprintf(buffer,BUFFER_SIZE, "indiquer deux noeuds\n");
      }else{
          graph__add_link(aquarium->graph, arguments[1], arguments[2]);
      }
    }
  else if (strcmp(arguments[0], "del") == 0)
    {
      if (arguments[1] == NULL || arguments[2] == NULL){
          snprintf(buffer,BUFFER_SIZE, "indiquer deux noeuds\n");
      }else{
          graph__del_link(aquarium->graph, arguments[1], arguments[2]);
      }
    }
  else if (strcmp(arguments[0], "remove") == 0)
    {
      if (arguments[1] == NULL){
          snprintf(buffer,BUFFER_SIZE, "pas de noeud indiqué\n");
      }else{
          graph__remove_node(aquarium->graph, arguments[1]);
      }
    }
  else if (strcmp(arguments[0], "save") == 0)
    {
      if (arguments[1] == NULL){
          snprintf(buffer,BUFFER_SIZE, "pas de fichier indiqué\n");
      }else{
          graph__save(aquarium->graph, arguments[1]);
      }
      
    }
  else if (strcmp(arguments[0], "exit") == 0){
    network__close();// ferme le network proprement
    exit(1);
  }
  else
    {
      snprintf(buffer, BUFFER_SIZE, "NOK : commande (%s) introuvable\n", arguments[0]);
    }

  for (int i = 0; arguments[i] != NULL; i++)
    {
      free(arguments[i]);
    }
  free(arguments);

  return buffer;
}


char* interface__compute_display_input(struct aquarium* aquarium, struct display* display, char* command){
    printf("compute command \"%s\"\n",command);

    char** arguments = parse_command(command);
    char* msg = parse_display_msg(arguments, aquarium, display);


    return msg;

}
