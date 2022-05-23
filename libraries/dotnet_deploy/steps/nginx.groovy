void call(){
    stage("nginx"){
        script {
            if (config.nginx == null) {
                return;
            }

            echo """command: ${config.nginx.command}"""
            echo """service_name: ${config.nginx.service_name}"""
        }
    }
}