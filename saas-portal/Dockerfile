FROM openjdk:11-jdk-slim-buster
VOLUME /tmp
COPY target/saas-portal-1.0.jar saas-portal.jar
ENTRYPOINT ["java","-jar","saas-portal.jar"]