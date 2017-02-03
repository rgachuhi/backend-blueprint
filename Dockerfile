FROM oli/wildfly-mysql
MAINTAINER Raphael Gachuhi, oli.cmu.edu
RUN ${JBOSS_HOME}/bin/add-user.sh -up mgmt-users.properties admin Admin#70365 --silent
ENV DEPLOYMENT_DIR ${JBOSS_HOME}/standalone/deployments/
COPY target/authorization.war $DEPLOYMENT_DIR
ENTRYPOINT ${JBOSS_HOME}/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0


