pipeline {
    agent any

    stages {
        stage('mvn_clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('mvn_compile') {
            steps {
                sh 'mvn compile'
            }
        }
       stage('SonarQube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
        stage('mockito_test') {
              steps {
                 sh 'mvn test'
              }
        }
        stage('nexus_deploy') {
              steps {
                 sh 'mvn deploy'
              }
       }
    }
}