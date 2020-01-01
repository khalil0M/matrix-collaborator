FROM openjdk:11.0.5-jre-slim
EXPOSE 8082
ADD /target/matrix-collaborator-0.0.1-SNAPSHOT.jar matrix-collaborator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","matrix-collaborator-0.0.1-SNAPSHOT.jar"]