appender('CONSOLE', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] - %msg%n"
    }
}

root(INFO, ["CONSOLE"])