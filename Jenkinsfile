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
                    error("Build stage failed. Aborting pipeline.")
                }
            }
        }

        stage("Deploy to Dev") {
            steps {
                echo "deploy to Dev"
            }
            post {
                failure {
                    error("Deploy to Dev stage failed. Aborting pipeline.")
                }
            }
        }

        stage("Deploy to QA") {
            steps {
                echo "deploy to qa"
            }
            post {
                failure {
                    error("Deploy to QA stage failed. Aborting pipeline.")
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
                    error("Run Regression Automation Tests stage failed. Aborting pipeline.")
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
                failure {
                    error("Publish Allure Reports stage failed. Aborting pipeline.")
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
                failure {
                    error("Publish Extent Report stage failed. Aborting pipeline.")
                }
            }
        }

        stage("Deploy to Stage") {
            steps {
                echo "deploy to Stage"
            }
            post {
                failure {
                    error("Deploy to Stage stage failed. Aborting pipeline.")
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
                    error("Run Sanity Automation Tests stage failed. Aborting pipeline.")
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
                failure {
                    error("Publish sanity Extent Report stage failed. Aborting pipeline.")
                }
            }
        }
    }
}
