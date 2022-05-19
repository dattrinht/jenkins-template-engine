import example.Utility

void call(){
    stage("Build ${config.service_name}"){
        script {
            def a = new Utility()
            echo "${a.s3_dir_location()}"
            echo """${config.service_name}"""
        }
    }
}