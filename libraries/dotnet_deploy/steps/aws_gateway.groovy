void call(){
    stage("aws_gateway"){
        script {
            echo """${config.aws_gateway.command}"""
        }
    }
}