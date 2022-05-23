void call(){
    stage("nginx"){
        script {
            echo """${config.nginx.command}"""
        }
    }
}