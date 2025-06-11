FROM openjdk:18

# Set working directory
WORKDIR /ai

# Define the version as a build argument
ARG VERSION=0.2.3
ARG REPO=SpringActions
ARG REPO_OWNER=vishalmysore

# Download the JAR file using curl with the version variable
RUN curl -L -o /ai/mcpdemo.jar https://github.com/${REPO_OWNER}/${REPO}/releases/download/release/${REPO}-${VERSION}.jar

# Expose the port
EXPOSE 7860

# Copy the entrypoint script to the container
COPY entrypoint.sh /entrypoint.sh

# Make the script executable
RUN chmod +x /entrypoint.sh

# Set the entrypoint
ENTRYPOINT ["/entrypoint.sh"]