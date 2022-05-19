void call(app_env){
    stage("Deploy ${config.service_name}") {
        app_env.deploy_server_instances.each { entry -> 
            def server_name = entry.key
            def server_ip = entry.value

            echo """deploy to server name: ${server_name} with ip: ${server_ip}"""
        }
    }
}