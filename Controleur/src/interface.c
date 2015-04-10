#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include "interface.h"
#include "graph.h"
#include "fish.h"


#define ARGUMENT_SIZE 10
#define BUFFER_SIZE 256

// fonctions internes
char** parse_command(char* buffer);
char* parse_display_msg(char** arguments);
char* parse_user_msg(char** arguments, struct aquarium*);
void* task_user_input(void* aquarium);


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


char* parse_display_msg(char** arguments)
{
  char* buffer = malloc(BUFFER_SIZE);
  buffer[0] = '\0';

  if (arguments[0] == NULL)
    {
      snprintf(buffer, BUFFER_SIZE, "pas de commande\n");
    }
  else if (strcmp(arguments[0], "hello") == 0) 
    {
      
    }
  else if (strcmp(arguments[0], "ping") == 0) 
    {
      
    }
  else if (strcmp(arguments[0], "status") == 0) 
    {
      
    }
  else if (strcmp(arguments[0], "addFish") == 0) 
    {
      
    }
  else if (strcmp(arguments[0], "delfish") == 0)
    {
      
    }
  else if (strcmp(arguments[0], "startFish") == 0)
    {
      
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
