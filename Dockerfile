# Use this as a base image
FROM openjdk:15

# Install tools
RUN microdnf install git
RUN microdnf install findutils
RUN microdnf install unzip

# Install Gradle
WORKDIR /app
RUN curl -L https://services.gradle.org/distributions/gradle-7.3.3-bin.zip -o gradle-7.3.3-bin.zip
RUN unzip gradle-7.3.3-bin.zip
ENV GRADLE_HOME=/app/gradle-7.3.3
ENV PATH=$PATH:$GRADLE_HOME/bin

# Build the app
WORKDIR /home/docker/
RUN git clone https://github.com/PKarppinen/tyt-api-repo.git
WORKDIR /home/docker/tyt-api-repo
RUN gradle build

# Run the built application
CMD java -jar build/libs/tyt-api-repo.jar --spring.data.mongodb.host=mongo --spring.data.mongodb.username=mongouser --spring.data.mongodb.password=mongopass