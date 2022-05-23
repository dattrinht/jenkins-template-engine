void call(){
    stage("dotnet_deploy"){
        script {
            suporvisor()
            nginx()
            crontab()
            aws_gateway()
        }
    }
}