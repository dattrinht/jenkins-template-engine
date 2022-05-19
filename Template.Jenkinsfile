pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                script {
                    git.check_out staging
                    build
                    deploy staging
                }
            }
        }
    }
}