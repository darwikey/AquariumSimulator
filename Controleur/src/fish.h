#ifndef FISH_H
#define FISH_H


struct fish{
  float x, y; // position
  float vx, vy; //vitesse
  char* name;
};

struct aquarium{
  struct fish** fishs;
  unsigned int fish_number;
};

// create a fish with a random speed
struct fish* create_fish(char* name, float x, float y);

//init aquarium
void init_aquarium(struct aquarium* a);

//add a fish in the aquarium
void add_fish(struct aquarium* a, struct fish* f);


#endif
