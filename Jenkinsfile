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
        stage ('MAVEN BUILD') {
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
        stage ('SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar';
            }
        }
        stage ('NEXUS') {
            steps {
                sh 'mvn deploy'
            }
        }
        stage('DOCKER IMAGES') {
            steps {
                sh 'docker build -t achatimage:v${BUILD_NUMBER} -f Dockerfile ./'
            }
        }
        stage ('DOCKER HUB') {
           steps {
               sh 'docker login -u oumaimaadmin -p docker123'
               sh 'docker tag achatimage:v${BUILD_NUMBER} oumaimaadmin/achatimage:achatimage'
               sh 'docker push oumaimaadmin/achatimage:achatimage'
           }
        }
        stage ('DOCKER-COMPOSE') {
            steps {
               sh 'docker compose up -d mysqldb'
               sh 'docker compose up -d app-achat-backend'
            }
        }
        stage ('PROMETHEUS & GRAFANA') {
            steps {
                sh 'docker compose up -d prometheus'
                sh 'docker compose up -d grafana'
            }
        }
    }
}
