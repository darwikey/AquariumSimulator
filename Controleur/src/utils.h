
enum log_level{
    LOG_NOTHING = 1,
    LOG_ERROR = 25,
    LOG_INFO = 75,
    LOG_ALL = 100,
};

void log_init();

void _log(enum log_level level, const char *fmt, ...)
        __attribute__((format (printf, 2, 3)));

#define log(level, fmt, ...) _log(level, fmt, ##__VA_ARGS__)

void set_log_level(enum log_level);
