void call(){
    stage("crontab"){
        script {
            if (config.crontab == null) {
                return;
            }

            echo """command: ${config.crontab.command}"""
            echo """service_name: ${config.crontab.service_name}"""
        }
    }
}