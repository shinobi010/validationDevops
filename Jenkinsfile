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
                sh 'docker build -t timesheetimage:v${BUILD_NUMBER} -f Dockerfile ./'
            }
        }
    }
}
