#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ARG 10
#define SIZE 256

int main(void)
{
   char **argv = NULL;
   char *saisie = malloc(sizeof(char) * SIZE);
   char *p = NULL;
   size_t i = 0;
   char c;
   /* On recupère la saisie */
   printf(">");
   fgets(saisie, SIZE, stdin);
   /* On supprime le de la fin */
   if(NULL != (p = strrchr(saisie, ' ')))
      *p = '\0';
   else
      while(' ' != (c = fgetc(stdin)) && c != EOF);

   /* On alloue argv */

   argv = malloc(sizeof(char *) * MAX_ARG);

   /* 
    * On lance le premier strtok
    * char *strtok(char *ptr, char *str);
    * ptr doit être la saisie qu'on veut parser
    * et str est une chaine qui contient les caractères
    * sur lesquels on va couper la saisie. Vous pouvez
    * bien sûr en mettre plusieurs
    */
   p = strtok(saisie, " ");
   while(p != NULL)
   {
      /* 
       * Ici p est un pointeur sur une chaine
       * qui contient exactement l'argument i
       */
      if(i < MAX_ARG)
      {
         argv[i] = malloc(sizeof(char) * (1+strlen(p)));
         strcpy(argv[i], p);
         i++;
      }
      else
         break;
      /* 
       * On lance un nouvel appel a strtok
       * par contre on lui donne en argument NULL
       * pour qu'il sache que c'est celle du dernier
       * appel, on peut aussi changer les caractères
       * pour parser...
       */
      p = strtok(NULL, " ");
   }

   argv[i] = NULL;

   /* On affiche les resultats */
   for(i = 0; argv[i] != NULL; i++)
   {
      printf("arg[%d] = '%s' ", i, argv[i]);
      free(argv[i]);
   }
   free(argv);
   free(saisie);
   return 0;
}