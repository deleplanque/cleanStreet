FROM tomcat:8.0

RUN rm -rf ${CATALINA_HOME}/webapps/ROOT/*
COPY ./dist ${CATALINA_HOME}/webapps/ROOT/
