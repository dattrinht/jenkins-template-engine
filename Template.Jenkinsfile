pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                pull_code git_repositories: env.git_repositories

                script {
                    for (program_name in env.build_program_names.split(", ")) {
                        build program_name: program_name, build_csproj_file_name: env.build_csproj_file_name
                        deploy staging
                    }
                }
            }
        }
    }
}