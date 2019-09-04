FROM tomcat:alpine
MAINTAINER Shrey Sangal
RUN wget -O /usr/local/tomcat/webapps/gameoflife.war http://192.168.56.104:9082/artifactory/test/com/wakaleo/gameoflife/gameoflife-web/1.0-SNAPSHOT/gameoflife-web-1.0-SNAPSHOT.war
EXPOSE 8080
