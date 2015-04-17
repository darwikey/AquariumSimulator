#ifndef INTERFACE_H
#define INTERFACE_H
#include "fish.h"

struct display {
int get_fish_continously;
int log_out;
char *buffer;//à libérer et réallouer à chaque fois lecture/écriture
};

void interface__wait_user_input(struct aquarium*);

char* interface__compute_display_input(struct aquarium*, struct display*, char* command);


#endif
