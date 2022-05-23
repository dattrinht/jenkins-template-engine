void call(){
    stage("aws_gateway"){
        script {
            if (config.aws_gateway == null) {
                return;
            }

            echo """command: ${config.aws_gateway.command}"""
            echo """service_name: ${config.aws_gateway.service_name}"""
        }
    }
}