# Use this as a base image
FROM openjdk:15

# Copy application
WORKDIR /home/docker/api
COPY build/libs/tyt-api-repo.jar /home/docker/api/
#CMD java -jar tyt-api-repo.jar