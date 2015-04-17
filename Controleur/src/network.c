#include "network.h"
#include "pthread.h"
#include "interface.h"
#define BUFFER_SIZE 256
#define LISTEN_MAX 5
#define THREAD_NB 16

struct thread_parameter{
  struct aquarium* aquarium;
  int sock;
};

void* network__wait(void* parameter);

void network__launch(uint16_t port_number, struct aquarium* aquarium){

  struct sockaddr_in dest; 
  
  int sock = socket(AF_INET, SOCK_STREAM, 0);
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


  pthread_t threads[THREAD_NB];

  struct thread_parameter param;
  param.aquarium = aquarium;
  param.sock = sock;

  for (int i=0; i < THREAD_NB; i++){
    pthread_create(&threads[i], NULL, network__wait, &param);
  }
  
  while(1);//TODO
  
  close(sock);
}


void* network__wait(void* parameter){
  struct thread_parameter *param = (struct thread_parameter*) parameter;
  

  /*struct sockaddr_in client_addr;
  memset(&client_addr, 0, sizeof(client_addr));    
  
  socklen_t client_addr_size = sizeof(client_addr);*/
  
  int client_sock = accept(param->sock, NULL, NULL);
  
  if(client_sock == INVALID_SOCKET){
    perror("accept()");
    exit(errno);
  }

  struct display display;
  display.get_fish_continously = 0;
  display.log_out = 0;
  display.buffer = NULL;

  char buffer[BUFFER_SIZE + 1]; // +1 so we can add null terminator  
  int used_buffer = 0;//number of char used in buffer
  while (!display.log_out){
      int lenght = recv(client_sock, buffer+used_buffer, BUFFER_SIZE - used_buffer, MSG_DONTWAIT);
      if (lenght >= 0){
          /* We have to null terminate the received data ourselves */
          buffer[lenght+used_buffer] = '\0';
          for (char* current = buffer+used_buffer; *current != '\0'; current ++){
              if (*current == '\r'){
                  printf("enfer et damnation ! il y a un \\r ! on est sous linux pourtant!\npour éviter ce problème on utilise une solution instable (cf %s ligne %d)\n", __FILE__, __LINE__);
                  *current = '\0';
              }
              if (*current == '\n'){
                  *current = '\0';
                  char * result = interface__compute_display_input(param->aquarium, &display, buffer);
                  write(client_sock, result, strlen(result));//TODO check for errors
                  free(result);
                  //then copy the chars after the \n to the start of buffer
                  used_buffer = 0;
                  lenght = 0;
                  for (current ++; *current !='\0';current ++){
                      buffer[used_buffer] = *current;
                      used_buffer ++;
                  }
                  buffer[used_buffer] = '\0';
                  break;
              }
          }

          used_buffer += lenght;
      }
      sleep(1);//to be removed
  }

  close(client_sock);
  return NULL;
}
