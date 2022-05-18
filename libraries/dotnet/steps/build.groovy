void call(){
    config.services.each() { service ->
        stage("Build ${service["service_name"]}"){
            script {
                echo """${service["service_name"]}"""
            }
        }
    }
}