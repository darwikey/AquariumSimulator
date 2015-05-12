#include "utils.h"
#include <stdarg.h>
#include <stdio.h>
#include <time.h>
#include <sys/time.h>

static enum log_level set_level = LOG_ALL;
static FILE* log_file = NULL;

void log_init(){
    log_file = fopen("Contrôleur.log","a");
}

void _log(enum log_level level, const char *fmt, ...) {
    va_list arg;
    //FILE *log_file = (level == LOG_ERROR) ? err_log : info_log;

    /* Check if the message should be logged */
    if (level > set_level)
        return;

    time_t current_time;
    struct tm l_tm;
    va_start(arg, fmt);
    time(&current_time);
    localtime_r(&current_time, &l_tm);
    fprintf(log_file,"[%d/%d/%d %d:%d:%d]",
            l_tm.tm_year%100,l_tm.tm_mon+1,l_tm.tm_mday,
            l_tm.tm_hour,l_tm.tm_min,l_tm.tm_sec);
    vfprintf(log_file, fmt, arg);
    va_end(arg);

}

void set_log_level(enum log_level level){
    set_level = level;
}
