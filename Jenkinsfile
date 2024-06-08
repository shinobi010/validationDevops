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
                                               sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=root"
                                           }
                                       }
                                        stage('Deploy to Nexus') {
                                                                      steps {
                                                                          sh 'mvn deploy'
                                                                     }
                                                                 }

                                                                            stage('Build Docker Image') {
                                                                                      steps {
                                                                                          script {
                                                                                              // Utilisation de sudo pour exécuter Docker
                                                                                             sh 'sudo docker build -t ghassenbax/achat:1.0 .'
                                                                                          }
                                                                                      }

                                                                                  }


                                                                                             stage('Login to Docker Hub') {
                                                                                                      steps {
                                                                                                             sh 'docker login -u ghassenbax -p adminadmin'

                                                                                                              sh 'docker push  ghassenbax/achat:1.0'
                                                                                                      }
                                                                                                  }
                                                                                                      stage('Deploy with Docker Compose') {
                                                                                                              steps {
                                                                                                                  script {
                                                                                                                      // Assurez-vous que vous êtes dans le répertoire contenant le fichier docker-compose.yml

                                                                                                                          // Exécutez docker-compose up -d
                                                                                                                          sh ' sudo docker-compose up -d'

                                                                                                                  }
                                                                                                              }
                                                                                                          }
                                                                                                      }

















}