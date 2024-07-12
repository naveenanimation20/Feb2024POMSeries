pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        SANITY_TESTS_RAN = false
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
                    script {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage("Deploy to Dev") {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "deploy to Dev"
            }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage("Deploy to QA") {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "deploy to qa"
            }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('Run Regression Automation Tests') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_regression.xml"
                }
            }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILURE'
                    }
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
        }

        stage("Deploy to Stage") {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "deploy to Stage"
            }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('Run Sanity Automation Tests') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Feb2024POMSeries.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/test_sanity.xml"
                }
                script {
                    env.SANITY_TESTS_RAN = true
                }
            }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('Publish sanity Extent Report') {
            when {
                expression { env.SANITY_TESTS_RAN == 'true' }
            }
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
