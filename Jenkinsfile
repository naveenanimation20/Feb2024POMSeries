pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            failFast true
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to Dev") {
            failFast true
            steps {
                echo "deploy to Dev"
            }
        }

        stage("Deploy to QA") {
            failFast true
            steps {
                echo "deploy to qa"
            }
        }

        stage('Run Regression Automation Tests') {
            failFast true
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_regression.xml"
                }
            }
        }

        stage('Publish Allure Reports') {
            failFast true
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
        }

        stage('Publish Extent Report') {
            failFast true
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Regression Extent Report',
                    reportTitles: ''])
            }
        }

        stage("Deploy to Stage") {
            failFast true
            steps {
                echo "deploy to Stage"
            }
        }

        stage('Run Sanity Automation Tests') {
            failFast true
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_sanity.xml"
                }
            }
        }

        stage('Publish sanity Extent Report') {
            failFast true
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Sanity Extent Report',
                    reportTitles: ''])
            }
        }
    }
}
