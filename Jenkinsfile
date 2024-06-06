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
                                stage('JaCoCo coverage report') {
                                            steps {
                                                 step([$class: 'JacocoPublisher',
                                                       execPattern: '**/target/jacoco.exec',
                                                      classPattern: '**/classes',
                                                     sourcePattern: '**/src',
                                                      exclusionPattern: '*/target/**/,**/*Test*,**/*_javassist/**'
                                                ])
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


        }












}