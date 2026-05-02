pipeline {
    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'Surefire Report'
                ])
            }
        }
    }
}
