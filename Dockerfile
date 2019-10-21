FROM tomcat:9.0-jre8-alpine
WORKDIR /app
COPY pom.xml .


ENV MAVEN_VERSION 3.5.4
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

#RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \

#COPY apache-maven-$MAVEN_VERSION-bin.tar.gz .


RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  mv apache-maven-$MAVEN_VERSION /usr/lib/mvn



RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B -Dmaven.repo.remote=https://nexus.internal.secureworks.net/repository/maven-central/ package


COPY target/*.war $CATALINA_HOME/webapps/
CMD ["catalina.sh", "run"]
