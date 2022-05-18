void call(){
    stage("Build ${config.services}"){
        script {
            echo """${config.services}"""
        }
    }
}