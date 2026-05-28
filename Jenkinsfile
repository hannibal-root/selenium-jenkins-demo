pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'buntabence',
                    url: 'https://github.com/hannibal-root/selenium-jenkins-demo.git'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile test-compile'
            }
        }

        stage('Regression Tests') {
            steps {
                // minden teszt lefut (API regression suite)
                sh 'mvn test surefire-report:report'
            }
        }

        stage('Generate Reports') {
            steps {
                sh 'mvn surefire-report:report'
            }
        }
    }

    post {

        always {
            // JUnit report Jenkins UI-hoz
            junit 'target/surefire-reports/*.xml'

            publishHTML(target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site',
                            reportFiles: 'surefire-report.html',
                            reportName: 'API Regression HTML Report'
                        ])

            // “snapshot” reportok
            archiveArtifacts artifacts: 'target/**/surefire-report.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/api-report.log', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
        }

        success {
            echo 'Regression suite SUCCESS ✔'
        }

        failure {
            echo 'Regression suite FAILED - check reports'
        }
    }
}