void call(){
    stage("crontab"){
        script {
            if (config.crontab == null) {
                return;
            }

            echo """${config.crontab.command}"""
        }
    }
}