pipeline {
    agent any
    tools  {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') {
            steps {
                git branch: 'productManagement-oumaimaChebbi',
                url : 'https://github.com/shinobi010/validationDevops.git'
            }
        }
        stage ('MVN CLEAN') {
            steps {
                sh 'mvn clean';
            }
        }
        stage ('MVN COMPILE') {
            steps {
                sh 'mvn compile';
            }
        }
    }
}
