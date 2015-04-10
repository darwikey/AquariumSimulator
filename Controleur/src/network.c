#include "network.h"
#include "pthread.h"
#define BUFFER_SIZE 256
#define LISTEN_MAX 5
#define THREAD_NB 16

struct thread_parameter{
  struct aquarium* aquarium;
  int sock;
};

// variables gloabale
static pthread_t threads[THREAD_NB];
static int sock = INVALID_SOCKET;

// fonctions internes
void* network__wait(void* parameter);


//lance les threads attendant un client
void network__launch(uint16_t port_number, struct aquarium* aquarium){

  struct sockaddr_in dest; 
  
  sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock == INVALID_SOCKET){
    perror("socket()");
    exit(errno);
  }
  
  
  memset(&dest, 0, sizeof(dest));    
  dest.sin_family = AF_INET;
  dest.sin_addr.s_addr = htonl(INADDR_ANY);
  dest.sin_port = htons(port_number); // set destination port number
  
  if (bind(sock, (struct sockaddr *)&dest, sizeof(struct sockaddr)) == SOCKET_ERROR){
    perror("bind()");
    exit(errno);
  }
  
  if(listen(sock, LISTEN_MAX) == SOCKET_ERROR){
    perror("listen()");
    exit(errno);
  }

  struct thread_parameter param;
  param.aquarium = aquarium;
  param.sock = sock;

  for (int i=0; i < THREAD_NB; i++){
    pthread_create(&threads[i], NULL, network__wait, &param);
  }
  
}


// ferme le socket
void network__close(void){
  close(sock);
}


// thread qui attend un client
void* network__wait(void* parameter){
  struct thread_parameter param = *(struct thread_parameter*) parameter;
  

  /*struct sockaddr_in client_addr;
  memset(&client_addr, 0, sizeof(client_addr));    
  
  socklen_t client_addr_size = sizeof(client_addr);*/
  
  int client_sock = accept(param.sock, NULL, NULL);
  
  if(client_sock == INVALID_SOCKET){
    perror("accept()");
    exit(errno);
  }


  char buffer[BUFFER_SIZE + 1];
  int lenght = recv(client_sock, buffer, BUFFER_SIZE, 0);
  
  buffer[lenght] = '\0';
  
  printf("Received %s (%d bytes).\n", buffer, lenght);
 

  close(client_sock);
  return NULL;
}
