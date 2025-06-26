// Jenkinsfile for my-fullstack-app

pipeline {
    agent any 

    environment {
        JAVA_HOME = tool 'JDK-21'
        M2_HOME = tool 'M3' 
        PATH = "${JAVA_HOME}/bin:${M2_HOME}/bin:${PATH}"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build Spring Boot Backend') {
            steps {
                script {
                    // Change directory into the CORRECT backend project folder
                    dir('backend') { // <-- CHANGED from 'backend'
                        echo 'Building Spring Boot Backend...'
                        if (isUnix()) {
                            sh './mvnw clean install -DskipTests'
                        } else {
                            bat 'mvnw.cmd clean install -DskipTests'
                        }
                        echo 'Spring Boot Backend Build Complete.'
                    }
                }
            }
        }

        stage('Build React Frontend') {
            steps {
                script {
                    // Change directory into the CORRECT frontend project folder
                    dir('frontend') { // <-- CHANGED from 'frontend'
                        echo 'Installing React Frontend dependencies...'
                        if (isUnix()) {
                            sh 'npm install'
                        } else {
                            bat 'npm install'
                        }

                        echo 'Building React Frontend...'
                        if (isUnix()) {
                            sh 'npm run build'
                        } else {
                            bat 'npm run build'
                        }
                        echo 'React Frontend Build Complete.'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
            deleteDir()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}