pipeline {
    agent any

    stages {
        stage('Git') {
            steps {
                script {
                    env.git_repositories.split(", ").each { item ->
                        def repo = item.split(" : ")
                        echo """Pull code from repo: ${repo[0]} on branch: ${repo[1]}"""
                    }
                    
                    for (program_name in env.build_program_names.split(", ")) {
                        stage("Build ${program_name}") {
                            echo """dotnet restore --verbosity quiet ${env.build_csproj_file_name}"""
                            echo """dotnet build --verbosity quiet --configuration Release --no-restore ${env.build_csproj_file_name}"""
                            echo """dotnet publish --verbosity quiet --configuration Release --no-build --output publish/${program_name} ${env.build_csproj_file_name}"""
                        }
                        
                        stage("Deploy ${program_name}") {
                            env.deploy_server_instances.split(", ").each { entry -> 
                                def instance = entry.split(" : ")
                                echo """deploy to server name: ${instance[0]} with ip: ${instance[1]}"""
                            }
                        }
                    }
                }
            }
        }
    }
}