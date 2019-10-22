FROM tomcat:9.0-jre8-alpine
WORKDIR /app
COPY pom.xml .

ENV http_proxy http://atl1webproxlb.corp.secureworks.net:3128
ENV https_proxy http://atl1webproxlb.corp.secureworks.net:3128

COPY target/*.war $CATALINA_HOME/webapps/
CMD ["catalina.sh", "run"]
