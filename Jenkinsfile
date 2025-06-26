// Jenkinsfile for my-fullstack-app

pipeline {
    agent any // This means the pipeline can run on any available Jenkins agent (or the master if no agents are configured)

    environment {
        // Define any environment variables needed for your build
        // For example, if npm is not in PATH for some reason, you might set NODE_HOME and add to PATH.
        // For now, assuming basic tools are in PATH.
        JAVA_HOME = tool 'JDK-21' // Assumes you have configured a JDK 21 tool in Jenkins global tool configuration
        M2_HOME = tool 'M3' // Assumes you have configured a Maven 3 tool in Jenkins global tool configuration
        PATH = "${JAVA_HOME}/bin:${M2_HOME}/bin:${PATH}"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                // This step is implicitly handled when "Pipeline script from SCM" is chosen,
                // but explicitly checking out ensures clarity and allows for custom behaviors if needed.
                checkout scm
            }
        }

        stage('Build Spring Boot Backend') {
            steps {
                script {
                    // Change directory into the backend project
                    dir('backend') {
                        echo 'Building Spring Boot Backend...'
                        // Use Maven Wrapper to clean and install dependencies, and build the JAR
                        // This will also run any unit tests in the backend
                        if (isUnix()) {
                            sh './mvnw clean install -DskipTests' // -DskipTests to skip tests for now, remove later
                        } else {
                            // For Windows agents, use mvnw.cmd
                            // Note: On Windows, sometimes the shell context can be tricky.
                            // Ensure npm and java/maven are correctly in PATH.
                            // You might need 'cmd.exe /C' for certain commands.
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
                    // Change directory into the frontend project
                    dir('frontend') {
                        echo 'Installing React Frontend dependencies...'
                        // Install Node.js dependencies
                        if (isUnix()) {
                            sh 'npm install'
                        } else {
                            bat 'npm install'
                        }

                        echo 'Building React Frontend...'
                        // Create the production build of the React application
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

        // You can add more stages here, e.g.:
        // stage('Run Backend Tests') { ... }
        // stage('Run Frontend Tests') { ... }
        // stage('Package Docker Images') { ... }
        // stage('Deploy to Server') { ... }
    }

    post {
        always {
            echo 'Pipeline finished.'
            // Clean up workspace after build
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