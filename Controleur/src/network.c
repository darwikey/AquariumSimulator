#include "network.h"
#include "pthread.h"
#include "interface.h"
#include <sys/time.h>
#include <unistd.h>
#include "utils.h"
#include "config.h"


#define BUFFER_SIZE 2048
#define LISTEN_MAX 5
#define THREAD_NB 16

struct thread_parameter{
  struct aquarium* aquarium;
  int sock;
};

// variables gloabale
static pthread_t threads[THREAD_NB];
static int sock = INVALID_SOCKET;
static struct thread_parameter param;

// fonctions internes
void* network__wait(void* parameter);
void network__timer_get_fish(struct timeval* timer, int client_sock, struct display* display, struct aquarium* aquarium);
void network__timer_log_out(struct timeval* timer, int client_sock, struct aquarium* a, struct display* display);


//lance les threads attendant un client
void network__launch(struct aquarium* aquarium){

  struct sockaddr_in dest; 
  
  sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock == INVALID_SOCKET){
    perror("socket()");
    exit(errno);
  }

 
  // supprime le message "Address already in use"
  int yes = 1;
  if(setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int)) == -1) {
    perror("setsockopt");
  }   
 
  memset(&dest, 0, sizeof(dest));    
  dest.sin_family = AF_INET;
  dest.sin_addr.s_addr = htonl(INADDR_ANY);
  dest.sin_port = htons(config__get_port()); // set destination port number
  
  if (bind(sock, (struct sockaddr *)&dest, sizeof(struct sockaddr)) == SOCKET_ERROR){
    perror("bind()");
    exit(errno);
  }
  
  if(listen(sock, LISTEN_MAX) == SOCKET_ERROR){
    perror("listen()");
    exit(errno);
  }

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
  struct thread_parameter *param = (struct thread_parameter*) parameter;
  
  while(1){
    int client_sock = accept(param->sock, NULL, NULL);
    if(client_sock == INVALID_SOCKET){
      perror("accept()");
      exit(errno);
    }

    struct display display;
    display.get_fish_continously = 0;
    display.log_out = 0;
    display.buffer = NULL;
    display.node = NULL;
    
    // buffer contenant les messages échangés
    char buffer[BUFFER_SIZE + 1]; 
    int used_buffer = 0;//number of char used in buffer
  
    // Initialisation des timers
    struct timeval get_fish_timer;
    gettimeofday(&get_fish_timer, NULL);
    struct timeval last_interaction_timer;
    last_interaction_timer = get_fish_timer;
    log(LOG_INFO,"client log in\n");

 
    while (!display.log_out){

      // reçoit des morceaux de message client
      int length = recv(client_sock, buffer+used_buffer, BUFFER_SIZE - used_buffer, MSG_DONTWAIT);

      if (length >= 0){
	// reset timer log out
	gettimeofday(&last_interaction_timer,NULL);
    	  
	buffer[length+used_buffer] = '\0';
          
	for (char* current = buffer+used_buffer; *current != '\0'; current ++){
	  // fix si jamais il y a un \r
	  if (*current == '\r'){
	    *current = '\0';
	  }

	  if (*current == '\n'){
	    *current = '\0';
	    char * result = interface__compute_display_input(param->aquarium, &display, buffer);
	    write(client_sock, result, strlen(result));
	    free(result);

	    //copie le caractère après le \n au debut du buffer
	    used_buffer = 0;
	    length = 0;
	    for (current ++; *current !='\0';current ++){
	      buffer[used_buffer] = *current;
	      used_buffer ++;
	    }
	    buffer[used_buffer] = '\0';
	    break;
	  }
	}

	used_buffer += length;
      }

      usleep(100);

      if (display.get_fish_continously){
	network__timer_get_fish(&get_fish_timer, client_sock, &display, param->aquarium);
      }

      network__timer_log_out(&last_interaction_timer, client_sock, param->aquarium,  &display);
    }


    // retire le client du graphe
    if (display.node)
      graph__node_disconnect(param->aquarium->graph, display.node);

    log(LOG_INFO,"client %s déconnecté\n",display.node ? graph__get_node_name(param->aquarium->graph, display.node) : "(inconu)");

    // ferme le socket
    close(client_sock);
  }

  return NULL;
}


void network__timer_get_fish(struct timeval* timer, int client_sock, struct display* display, struct aquarium* aquarium){
  struct timeval current_time;
  gettimeofday(&current_time, NULL);

  if (current_time.tv_sec > timer->tv_sec + config__get_fish_update()){
    // reset du timer
    gettimeofday(timer, NULL);

    // envoie de la liste de poissons
    char tmp[BUFFER_SIZE] = {0};
    if (display->node != NULL){
      fish__get_fishes(aquarium, display->node, tmp, BUFFER_SIZE);
    }
    write(client_sock, tmp, strlen(tmp));
  }
}


void network__timer_log_out(struct timeval* timer, int client_sock, struct aquarium*a, struct display* display){
  struct timeval current_time;
  gettimeofday(&current_time, NULL);

  if (current_time.tv_sec > timer->tv_sec + config__get_display_timeout()){
    display->log_out = 1;

    char tmp[BUFFER_SIZE] = {0};
    snprintf(tmp, BUFFER_SIZE, "pas d'interaction depuis %d secondes. La connexion va être interrompue.\nLa prochaine fois utilisez la commande ping\n", config__get_display_timeout());
    write(client_sock, tmp, strlen(tmp));

    log(LOG_INFO,"client %s inactif depuis %d secondes\n",display->node ? graph__get_node_name(a->graph, display->node) : "(inconu)", config__get_display_timeout());
  }
}
