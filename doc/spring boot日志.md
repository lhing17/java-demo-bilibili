1. 使用了commons-logging门面，可以自己选择实现，包括java util logging，log4j2，logback等。
2. 如果使用了starter，会自动配置一个logback的实现，日志输出到控制台和文件。
3. 日志格式：
 - 日期时间 %d{yyyy-MM-dd HH:mm:ss.SSS}，默认格式为ISO8601，也可以自定义格式
 - 日志级别 %level
 - 进程ID ${PID}
 - 线程名 %thread
 - 日志内容 %msg%n
 - 日志记录器名称 %logger{50}
4. 日志级别：trace < debug < info < warn < error。logback中没有fatal级别，fatal级别的日志会被输出为error级别。
5. 要开启debug模式，可以在application.properties中设置debug=true，也可以在启动时设置--debug参数。同理trace。
6. 彩色日志：在application.properties中设置spring.output.ansi.enabled=always。可以使用%clr来输出彩色日志，将对应级别的日志映射成对应的颜色。
7. %clr后面也可以指定具体的颜色，支持的颜色包括blue、cyan、faint、green、magenta、red、yellow
8. 输出到文件：在application.properties中设置logging.file.name和logging.file.path。如果只设置name，会输出到当前目录下。如果只设置path，在指定目录下生成spring.log文件。如果都不设置，不生成文件。
9. 文件滚动策略，仅支持配置logback，如果使用了log4j2，需要自己配置。
10. 可以使用logging.level.包名=级别来设置日志级别。如果不设置，会使用root的日志级别，只能指定到包级别。
11. 可以使用logging.group.组名=包名,包名,包名将多个包整合成一个日志组，对组统一设置日志级别，spring boot内置了两个组，分别是web和sql。
12. 
13. Logging System	Customization
    Logback:logback-spring.xml, logback-spring.groovy, logback.xml, or logback.groovy
Log4j2 : log4j2-spring.xml or log4j2.xml
JDK (Java Util Logging) : logging.properties
