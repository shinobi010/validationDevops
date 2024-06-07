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
                                                                  stage('DOCKER IMAGES') {
                                                                             steps {
                                                                                 sh 'docker build -t achatimage:v${BUILD_NUMBER} -f Dockerfile ./'
                                                                             }
                                                                         }
                                                                             stage('Build Docker Image') {
                                                                                     steps {
                                                                                         script {
                                                                                             // Étape de construction de l'image Docker
                                                                                             sh 'docker build -t ghassenbenamor/tpachatproject:1.0 .'
                                                                                         }
                                                                                     }
                                                                                 }




        }












}