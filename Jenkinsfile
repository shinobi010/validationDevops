pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }



    stages {
        stage('Checkout Git repository') {
            steps {
                echo 'Pulling'
                git branch: 'ghassen', url:'https://github.com/shinobi010/validationDevops.git'
            }
        }

        stage('Maven Install') {
            steps {
                sh 'mvn install'
            }
        }

         stage('Build package') {
                    steps {
                        sh 'mvn package'
                    }
                }


                stage('Rapport JaCoCo') {

                                   steps {
                                        sh 'mvn jacoco:report'
                                    }
                                }
                                   stage('SonarQube Analysis') {
                                                      steps {
                                                          withCredentials([string(credentialsId: 'scanner', variable: 'SONAR_TOKEN')]) {
                                                              sh 'mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN'
                                                          }
                                                      }
                                                  }


        }












}