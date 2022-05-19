void call(){
    stage("Build ${config.service_name}"){
        script {
            echo "${utility.s3_dir_location()}"
            echo """${config.service_name}"""
        }
    }
}