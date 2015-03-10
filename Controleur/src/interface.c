#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "interface.h"

#define ARGUMENT_SIZE 10
#define BUFFER_SIZE 256

void parse_command(void)
{
	char* buffer = malloc(BUFFER_SIZE);

	printf("$ ");
	fgets(buffer, BUFFER_SIZE, stdin);

	char* pos = strrchr(buffer, '\n');
	if (pos != NULL)
	{
		*pos = '\0';
	}

	char** arguments = malloc(ARGUMENT_SIZE * sizeof(char *));
	for (int i = 0; i < ARGUMENT_SIZE; i++)
	{
		arguments[i] = NULL;
	}

	// découpe la chaine à chaque espace
	char* p = strtok(buffer, " ");
	int i = 0;
	while (p != NULL)
	{
		if (i < ARGUMENT_SIZE)
		{
			arguments[i] = malloc(1 + strlen(p));
			strcpy(arguments[i], p);
			i++;
		}
		else
		{
			break;
		}

		// découpe la chaine à chaque espace
		p = strtok(NULL, " ");
	}

	free(buffer);

	if (strcmp(arguments[0], "status") == 0)
	{
	
	}


	// On affiche les resultats
	for (i = 0; arguments[i] != NULL; i++)
	{
		printf("arg[%d] = '%s' ", i, arguments[i]);
		free(arguments[i]);
	}
	free(arguments);
	
}