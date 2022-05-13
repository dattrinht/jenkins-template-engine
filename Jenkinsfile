pipeline {
    agent any

    stages {
        stage('Git') {
            steps {
                git branch: "main", credentialsId: "github", poll: false, url: """https://github.com/dattrinht/jenkins-template-engine.git"""
                sh "ls -la"
                sh "cd.."
                sh "ls -la"
                sh "cd.."
                sh "ls -la"
            }
        }

        stage('Build') {
            steps {
                echo "Building... ${env.param1}"
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}