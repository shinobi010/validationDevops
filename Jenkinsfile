pipeline {
    agent any
    tools  {
        maven 'M2_HOME'
    }
    stages {
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
        stage ('MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('NEXUS') {
            steps {
                sh 'mvn deploy'
            }
        }
        stage('DOCKER IMAGES') {
            steps {
                sh 'docker build --build-arg NEXUS_USERNAME=admin --build-arg NEXUS_PASSWORD=sonatype -t achatimage:v${BUILD_NUMBER} -f Dockerfile ./'
            }
        }
        stage ('DOCKER HUB') {
            steps {
                 sh 'docker login -u hadhemidoghri -p B81b82b83'
                 sh 'docker tag achatimage:v${BUILD_NUMBER} hadhemidoghri/achatimage:latest'
                 sh 'docker push hadhemidoghri/achatimage:latest'
            }
         }
         stage ('DOCKER-COMPOSE') {
             steps {
                sh 'docker compose up -d'
             }
         }
    }
}
