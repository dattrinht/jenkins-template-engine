void call(){
    stage("suporvisor"){
        script {
            if (config.suporvisor == null) {
                return;
            }

            echo """${config.suporvisor.command}"""
        }
    }
}