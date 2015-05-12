#include <stdio.h>
#include <stdlib.h>
#include "interface.h"
#include "fish.h"
#include "network.h"
#include "time.h"
#include "graph.h"
#include "utils.h"


int main(int argc, char** argv)
{
  // initialise les nombres aléatoires
  srand(time(NULL));

  log_init();

  struct aquarium aquarium;
  fish__init_aquarium(&aquarium);

  char *default_graph = "data/default.dot";
  if (argc > 1){
      default_graph = argv[1];
  }
  graph__load(default_graph, &aquarium.graph);
 
  interface__wait_user_input(&aquarium);

  network__launch(4242, &aquarium);

  fish__update(&aquarium);

  return EXIT_SUCCESS;
}
