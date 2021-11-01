import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

def CONSOLE_LOG_PATTERN = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}"
appender("console", ConsoleAppender) {
  filter(ThresholdFilter) {
    level = DEBUG
  }
  encoder(PatternLayoutEncoder) {
    pattern = "${CONSOLE_LOG_PATTERN}"
    charset = Charset.forName("utf8")
  }
}
appender("flatfile", RollingFileAppender) {
  file = "${LOG_FILE}"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${LOG_FILE}.%d{yyyy-MM-dd}.gz"
    maxHistory = 7
  }
  encoder(PatternLayoutEncoder) {
    pattern = "${CONSOLE_LOG_PATTERN}"
    charset = Charset.forName("utf8")
  }
}
root(INFO, ["console"])
logger("com.example.gateway", DEBUG)