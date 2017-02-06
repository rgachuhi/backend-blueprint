node {
  checkout scm
  env.PATH = "${tool 'Maven3'}/bin:${env.PATH}"
  //stage('Package') {
   // sh 'mvn clean package -DskipTests'
  //}

  stage('Create Docker Image') {
    sh 'docker build -t oli/authorization .'
  }

  stage ('Run Application') {
    try {
      sh 'docker-compose up -d'
    } catch (error) {
    } finally {

    }
  }

  stage('Run Tests') {
    try {
        sh 'mvn clean test -Parq-wildfly-remote'
    } catch (error) {

    } finally {
      sh 'docker-compose down'
      junit '**/target/surefire-reports/*.xml'
    }
  }
}