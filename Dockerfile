FROM oli/wildfly-mysql
MAINTAINER Raphael Gachuhi, oli.cmu.edu
ENV DEPLOYMENT_DIR ${JBOSS_HOME}/standalone/deployments/
ADD execute.sh ${JBOSS_HOME}/
USER root
RUN chmod +x ${JBOSS_HOME}/execute.sh
USER jboss
#COPY target/authorization.war $DEPLOYMENT_DIR
ENTRYPOINT ${JBOSS_HOME}/execute.sh



