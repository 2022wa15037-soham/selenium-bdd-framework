pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven3'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/2022wa15037-soham/selenium-bdd-framework.git'
            }
        }

        stage('Run BDD Automation Tests') {
            steps {
                bat 'mvn clean test -Dheadless=true'
            }
        }
    }

    post {
        always {
            // Publish Aventstack Extent Spark HTML Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/SparkReport',
                reportFiles: 'Spark.html',
                reportName: 'Extent Spark Report'
            ])

            // Publish Built-in Cucumber HTML Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber HTML Report'
            ])
        }
    }
}
