void call(){
    stage("Dotnet: Build"){
        println "build from the dotnet librar - param: ${env.param1}"
    }
}