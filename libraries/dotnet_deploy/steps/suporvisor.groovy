void call(){
    stage("suporvisor"){
        script {
            echo """${config.suporvisor.command}"""
        }
    }
}