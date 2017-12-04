FROM openjdk:8

COPY ./target/*.jar /usr/src/myapp/
RUN chmod +x /usr/src/myapp/*.jar
RUN ls -lh /usr/src/myapp/

ENTRYPOINT ["java", "-jar", "/usr/src/myapp/CleanStreet-0.0.1-SNAPSHOT.jar"]
CMD [""]
