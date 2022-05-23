void call(){
    stage("dotnet_deploy"){
        script {
            nginx()
            suporvisor()
        }
    }
}