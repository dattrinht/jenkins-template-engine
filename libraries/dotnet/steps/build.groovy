void call(){
    stage("Build ${config.services["service_name"]}"){
        script {
            echo """${config.services["service_name"]}"""
        }
    }
}