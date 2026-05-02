pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Deepasree-S14/api-automation-framework.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'index.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
}