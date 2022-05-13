void call(){
    stage("Dotnet: Build"){
        println "workspace: ${env.WORKSPACE}"
        println "build from the dotnet librar - param: ${env.param1}"
    }
}