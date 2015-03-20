#include <stdio.h>
#include <stdlib.h>
#include "interface.h"
#include "fish.h"
#include "network.h"
#include "time.h"


int main(int argc, char** argv)
{
  srand(time(NULL));

  struct aquarium aquarium;
  init_aquarium(&aquarium);
 
  network__launch(4242, &aquarium);

  //  wait_user_input();

  return EXIT_SUCCESS;
}
