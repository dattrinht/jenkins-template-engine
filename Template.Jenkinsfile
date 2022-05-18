pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                script {
                    git.check_out staging
                    
                    for (program_name in env.build_program_names.split(", ")) {
                        build program_name: program_name, build_csproj_file_name: env.build_csproj_file_name
                        deploy program_name: program_name, staging
                    }
                }
            }
        }
    }
}