FROM openjdk:8

COPY ./target/*.jar /usr/src/myapp/
RUN chmod +x /usr/src/myapp/*.jar
RUN ls -lh /usr/src/myapp/

ENTRYPOINT ["java", "-jar", "/usr/src/myapp/webApp-1.0-RELEASE.jar"]
CMD [""]
