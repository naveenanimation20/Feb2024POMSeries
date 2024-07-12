pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    echo "Build stage failed."
                }
            }
        }

        stage("Deploy to Dev") {
            steps {
                echo "deploy to Dev"
            }
            post {
                failure {
                    echo "Deploy to Dev stage failed."
                }
            }
        }

        stage("Deploy to QA") {
            steps {
                echo "deploy to qa"
            }
            post {
                failure {
                    echo "Deploy to QA stage failed."
                }
            }
        }

        stage('Run Regression Automation Tests') {
            steps {
                git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_regression.xml"
            }
            post {
                failure {
                    echo "Run Regression Automation Tests stage failed."
                }
            }
        }

        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
            post {
                always {
                    echo "Publishing Allure Reports"
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Regression Extent Report',
                    reportTitles: ''])
            }
            post {
                always {
                    echo "Publishing Extent Reports"
                }
            }
        }

        stage("Deploy to Stage") {
            steps {
                echo "deploy to Stage"
            }
            post {
                failure {
                    echo "Deploy to Stage stage failed."
                }
            }
        }

        stage('Run Sanity Automation Tests') {
            steps {
                git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_sanity.xml"
            }
            post {
                failure {
                    echo "Run Sanity Automation Tests stage failed."
                }
            }
        }

        stage('Publish sanity Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Sanity Extent Report',
                    reportTitles: ''])
            }
            post {
                always {
                    echo "Publishing Sanity Extent Reports"
                }
            }
        }
    }
}
