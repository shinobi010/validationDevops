pipeline {
    agent any

    tools {

                mavenTool 'Maven 3.6.3' // Spécifie la version de Maven à utiliser
                jdk 'JDK8' // Spécifie la version de Java à utiliser

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


        }












}