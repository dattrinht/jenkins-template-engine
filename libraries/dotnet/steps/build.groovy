void call(){
    stage("Build ${config.services["service_name"]}"){
        script {
            def a = app_static.s3_dir_location()
            echo "${a}"
            echo """${config.services["service_name"]}"""
        }
    }
}