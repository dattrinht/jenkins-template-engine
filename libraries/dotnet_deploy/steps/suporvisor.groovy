void call(){
    stage("suporvisor"){
        script {
            if (config.suporvisor == null) {
                return;
            }

            echo """command: ${config.suporvisor.command}"""
            echo """service_name: ${config.suporvisor.service_name}"""
        }
    }
}