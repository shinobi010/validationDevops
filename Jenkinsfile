pipeline {
    agent any

    environment {
            RECIPIENTS = 'marwensnoussi@gmail.com'
        }

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
       stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
        stage('Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }

    }
     post {
            success {
                emailext(
                    subject: "Jenkins Pipeline: ${currentBuild.fullDisplayName} Succeeded",
                    body: "Good news! The Jenkins pipeline ${env.JOB_NAME} build number ${env.BUILD_NUMBER} succeeded.",
                    to: "${env.RECIPIENTS}"
                )
            }

            failure {
                emailext(
                    subject: "Jenkins Pipeline: ${currentBuild.fullDisplayName} Failed",
                    body: "Unfortunately, the Jenkins pipeline ${env.JOB_NAME} build number ${env.BUILD_NUMBER} failed. Please check the logs for more details.",
                    to: "${env.RECIPIENTS}"
                )
            }

            always {
                emailext(
                    subject: "Jenkins Pipeline: ${currentBuild.fullDisplayName} Finished",
                    body: "The Jenkins pipeline ${env.JOB_NAME} build number ${env.BUILD_NUMBER} has finished.",
                    to: "${env.RECIPIENTS}"
                )
            }
        }
}
