pipeline {
    agent any
    tools  {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') {
            steps {
                git branch: 'main',
                url : 'https://github.com/shinobi010/validationDevops.git'
            }
        }
        stage ('MAVEN BUILD') {
            steps {
                sh 'mvn clean';
                sh 'mvn compile';
            }
        }
        stage ('SONARQUBE') {
            steps {
               sh "mvn sonar:sonar \
                -Dsonar.projectKey=achat \
                -Dsonar.host.url=http://192.168.56.2:9000 \
                -Dsonar.login=20c82a045fc598455bfd04989f372b31513e5f8e"
            }
        }
    }
}