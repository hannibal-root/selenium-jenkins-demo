pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/hannibal-root/selenium-jenkins-demo.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {

        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Selenium teszt sikeres!'
        }

        failure {
            echo 'Selenium teszt sikertelen!'
        }
    }
}