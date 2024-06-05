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
       stage('building image') {
             steps {
                   sh 'docker build -t achatstockimage:v${BUILD_NUMBER} -f Dockerfile ./'
             }
       }
        stage('deploy image'){
             steps {
                    sh 'docker login -u eyamagdouli -p docker123'
                    sh 'docker tag achatimage:v${BUILD_NUMBER} eyamagdouli/achatstockimage:achatstockimage'
                    sh 'docker push  eyamagdouli/achatstockimage:achatstockimage'
             }
        }
        stage('docker_compose'){
             steps {
                    sh 'docker compose up -d'
             }
        }

    }
}