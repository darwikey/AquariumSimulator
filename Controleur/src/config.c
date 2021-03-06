#include "config.h"
#include "ini_parser.h"
#include "utils.h"
#include "string.h"
#include "stdlib.h"
#include "stdio.h"

struct config{
  int port;
  int display_timeout;
  int fish_update;
};

static struct config config = {0};


static int config__parser_handler(void* data, const char* section, const char* name, const char* value) {
    struct config* config = (struct config*)data;

    if (strcmp("port", name) == 0){
      config->port = atoi(value);
    }
    else if (strcmp("display-timeout", name) == 0){
      config->display_timeout = atoi(value);
    }
    else if (strcmp("fish-update", name) == 0){
      config->fish_update = atoi(value);
    }
    return 1;
}


void config__init(){
  log(LOG_INFO, "Charge config"); 

  if (ini_parse("controleur.ini", config__parser_handler, &config) < 0){
    fprintf(stderr, "impossible de charger controller.ini");
    exit(EXIT_FAILURE);
  }  
}


int config__get_port(){
  return config.port;
}


int config__get_display_timeout(){
  return config.display_timeout;
}


int config__get_fish_update(){
  return config.fish_update;
}
