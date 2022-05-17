void call(Map config){
    stage("Build ${config.program_name}"){
        script {
            echo """dotnet restore --verbosity quiet ${config.build_csproj_file_name}"""
            echo """dotnet build --verbosity quiet --configuration Release --no-restore ${config.build_csproj_file_name}"""
            echo """dotnet publish --verbosity quiet --configuration Release --no-build --output publish/${config.program_name} ${config.build_csproj_file_name}"""
        }
    }
}