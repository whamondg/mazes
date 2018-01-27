appender('CONSOLE', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] - %msg%n"
    }
}

//if (System.getenv().env == 'DEV') {
    root(DEBUG, ["CONSOLE"])
//}