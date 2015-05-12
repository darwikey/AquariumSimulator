#include "utils.h"
#include <stdarg.h>
#include <stdio.h>
#include <time.h>
#include <sys/time.h>

static enum log_level set_level = LOG_ALL;
static FILE* log_file = NULL;

void log_init(){
    log_file = fopen("Controleur.log","w");
}

void _log(enum log_level level, const char *fmt, ...) {
    va_list arg;

    /* Check if the message should be logged */
    if (level > set_level)
        return;

    time_t current_time;
    struct tm l_tm;
    va_start(arg, fmt);
    time(&current_time);
    localtime_r(&current_time, &l_tm);
    fprintf(log_file,"[%2d/%02d/%02d %2d:%02d:%02d]",
            l_tm.tm_year%100,l_tm.tm_mon+1,l_tm.tm_mday,
            l_tm.tm_hour,l_tm.tm_min,l_tm.tm_sec);
    vfprintf(log_file, fmt, arg);
    va_end(arg);
    fflush(log_file);
}

void set_log_level(enum log_level level){
    set_level = level;
}

void log_close(){
    fclose(log_file);
}
