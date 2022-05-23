void call(){
    stage("dotnet_deploy"){
        script {
            if (config.suporvisor != null) {
                suporvisor()
            }

            if (config.nginx != null) {
                nginx()
            }

            if (config.crontab != null) {
                crontab()
            }

            if (config.aws_gateway != null) {
                aws_gateway()
            }
        }
    }
}