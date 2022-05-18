pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                pull_code git_repositories: env.git_repositories

                script {
                    def buildCommandSupervisor = command_builder.buildCommandSupervisor("aaa", "bbbb")
                    println "${buildCommandSupervisor}"
                    // def buildCommandDeploy = command_builder.buildCommandDeploy OBJECT_S3: OBJECT_S3, SERVICE_NAME: SERVICE_NAME
                    // println "${buildCommandDeploy}"
                    // def buildSupervisorConfigFile = command_builder.buildSupervisorConfigFile OBJECT_S3: OBJECT_S3, SERVICE_NAME: SERVICE_NAME
                    // println "${buildSupervisorConfigFile}"
                    for (program_name in env.build_program_names.split(", ")) {
                        build program_name: program_name, build_csproj_file_name: env.build_csproj_file_name
                        deploy program_name: program_name, staging
                    }
                }
            }
        }
    }
}