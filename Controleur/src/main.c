#include <stdio.h>
#include <stdlib.h>
#include "interface.h"
#include "fish.h"
#include "network.h"
#include "time.h"
#include "graph.h"


int main(int argc, char** argv)
{
  srand(time(NULL));

  struct aquarium aquarium;
  init_aquarium(&aquarium);
 

  wait_user_input(&aquarium);

  return EXIT_SUCCESS;
}
