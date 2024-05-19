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
        stage ('BUILD') {
            steps {
                sh 'mvn clean';
                sh 'mvn compile';
            }
        }
        stage ('MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
