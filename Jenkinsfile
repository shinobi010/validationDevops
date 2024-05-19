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
        stage ('SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true';
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
               sh 'docker compose up -d'
            }
        }
        stage ('GRAFANA') {
            steps {
               echo 'GRAFANA'
            }
        }
    }
    post {
        success {
            mail bcc: '',
            body: 'Pipeline build succeeded',
            cc: '',
            from: 'hadhouma2.pfe@yahoo.com',
            replyTo: '',
            subject: 'Build Finished - Success',
            to: 'jaziri.aziz@yahoo.com'
        }
        failure {
            mail bcc: '',
            body: 'Pipeline build failed',
            cc: '',
            from: 'hadhouma2.pfe@yahoo.com', 
            replyTo: '',
            subject: 'Build Finished - Failure', 
            to: 'jaziri.aziz@yahoo.com'
        }
        always {
            emailext attachLog: true, body: '', subject: 'Build finished', from: 'hadhouma2.pfe@yahoo.com', to: 'jaziri.aziz@yahoo.com'
            cleanWs()
        }
    }
}
