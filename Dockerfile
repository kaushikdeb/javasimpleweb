FROM MAVEN:3.6-jdk-8-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package


FROM tomcat:9.0-jre8-alpine

COPY --from=builder target/*.war $CATALINA_HOME/webapps/
CMD ["catalina.sh", "run"]
