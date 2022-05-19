import src.app_static

void call(){
    stage("Build ${config.service_name}"){
        script {
            def a = app_static.s3_dir_location()
            echo "${a}"
            echo """${config.service_name}"""
        }
    }
}