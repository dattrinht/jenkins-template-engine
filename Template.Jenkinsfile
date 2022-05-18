pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                script {
                    git.check_out staging
                    
                    for (program_name in env.build_program_names.split(", ")) {
                        build
                        deploy program_name: program_name, staging
                    }
                }
            }
        }
    }
}