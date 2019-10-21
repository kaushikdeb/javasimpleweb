FROM tomcat:9.0-jre8-alpine
WORKDIR /app
COPY pom.xml .


#ENV MAVEN_VERSION 3.5.4
#ENV MAVEN_HOME /usr/lib/mvn
#ENV PATH $MAVEN_HOME/bin:$PATH

ENV http_proxy http://atl1webproxlb.corp.secureworks.net:3128
ENV https_proxy http://atl1webproxlb.corp.secureworks.net:3128

#RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
#  tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \

#  rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
#  mv apache-maven-$MAVEN_VERSION /usr/lib/mvn

COPY maven-settings.xml /usr/lib/mvn/conf/settings.xml

#RUN mvn -e -B dependency:resolve
#COPY src ./src
#RUN mvn -e -B -X package

COPY target/*.war $CATALINA_HOME/webapps/
CMD ["catalina.sh", "run"]
