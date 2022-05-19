pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                script {
                    git.check_out staging
                    build
                    for (program_name in env.build_program_names.split(", ")) {
                        deploy program_name: program_name, staging
                    }
                }
            }
        }
    }
}