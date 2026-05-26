pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/hannibal-root/selenium-jenkins-demo.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test site'
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'index.html',
                    reportName: 'API Test Report'
                ])
            }
        }
    }

    post {

        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Teszt sikeres!'
        }

        failure {
            echo 'Teszt sikertelen!'
        }
    }
}