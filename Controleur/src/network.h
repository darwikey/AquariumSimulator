#ifndef NETWORK_H
#define NETWORK_H


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <errno.h>
#include "fish.h"

#define INVALID_SOCKET -1
#define SOCKET_ERROR -1


void network__launch(struct aquarium* aquarium);

void network__close(void);

#endif
