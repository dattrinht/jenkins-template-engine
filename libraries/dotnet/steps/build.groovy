void call(app_env){
    stage("Build ${config.service_name}"){
        script {
            echo "${utility.s3_dir_location()}"
            echo """${config.timestamp}"""
            echo """${config.timestamp}"""
            echo """${config.timestamp}"""
            echo """${config.timestamp}"""
            echo """${config.timestamp}"""
            echo """${config.timestamp}"""
        }
    }
}