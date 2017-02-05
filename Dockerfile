FROM jboss/wildfly:latest
MAINTAINER Raphael Gachuhi, oli.cmu.edu
ADD changeDatabase.xsl ${JBOSS_HOME}/
RUN java -jar /usr/share/java/saxon.jar -s:${JBOSS_HOME}/standalone/configuration/standalone.xml -xsl:${JBOSS_HOME}/changeDatabase.xsl -o:${JBOSS_HOME}/standalone/configuration/standalone.xml; java -jar /usr/share/java/saxon.jar -s:${JBOSS_HOME}/standalone/configuration/standalone-ha.xml -xsl:${JBOSS_HOME}/changeDatabase.xsl -o:${JBOSS_HOME}/standalone/configuration/standalone-ha.xml; rm ${JBOSS_HOME}/changeDatabase.xsl
RUN mkdir -p ${JBOSS_HOME}/modules/system/layers/base/com/mysql/jdbc/main; cd ${JBOSS_HOME}/modules/system/layers/base/com/mysql/jdbc/main && curl -O http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.40/mysql-connector-java-5.1.40.jar
ADD module.xml ${JBOSS_HOME}/modules/system/layers/base/com/mysql/jdbc/main/
ENV DEPLOYMENT_DIR ${JBOSS_HOME}/standalone/deployments/
ADD execute.sh ${JBOSS_HOME}/
USER root
RUN chmod +x ${JBOSS_HOME}/execute.sh
USER jboss
#COPY target/authorization.war $DEPLOYMENT_DIR
ENTRYPOINT ${JBOSS_HOME}/execute.sh
