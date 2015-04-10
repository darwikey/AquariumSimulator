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
  fish__init_aquarium(&aquarium);
 
  interface__wait_user_input(&aquarium);

  network__launch(4242, &aquarium);


  return EXIT_SUCCESS;
}
