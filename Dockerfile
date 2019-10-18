FROM tomcat:9.0-jre8-alpine

COPY target/*.war $CATALINA_HOME/webapps/
