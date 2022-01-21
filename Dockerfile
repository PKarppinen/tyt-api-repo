# Use this as a base image
FROM java:openjdk-7-jre

# Copy application
WORKDIR /home/docker/api
COPY api.jar /home/docker/api/
CMD java -jar api.jar