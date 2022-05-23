void call(){
    stage("nginx"){
        script {
            if (config.nginx == null) {
                return;
            }
            
            echo """${config.nginx.command}"""
        }
    }
}