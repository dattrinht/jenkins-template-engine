pipeline {
    agent any

    parameters {
        string(name: 'STATEMENT', defaultValue: 'hello; ls /', description: 'What should I say?')
  }

    stages {
        stage('Git') {
            steps {
                echo 'Pulling..'
            }
        }

        stage('Build') {
            steps {
                echo 'Building.. ${params.STATEMENT}'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}