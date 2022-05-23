void call(){
    stage("aws_gateway"){
        script {
            if (config.aws_gateway == null) {
                return;
            }

            echo """${config.aws_gateway.command}"""
        }
    }
}