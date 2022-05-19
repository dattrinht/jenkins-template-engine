import src.app_static

void call(){
    stage("Build ${config.service_name}"){
        script {
            def a = new app_static()
            echo "${a.s3_dir_location()}"
            echo """${config.service_name}"""
        }
    }
}