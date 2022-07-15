# Base Java image
FROM williamyeh/java8

EXPOSE 8090

COPY /corsproxy-1.0-SNAPSHOT-exec.jar /local/corsproxy-1.0-SNAPSHOT-exec.jar

USER root

CMD ["java","-jar","/local/corsproxy-1.0-SNAPSHOT-exec.jar"]
