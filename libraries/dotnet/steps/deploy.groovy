void call(app_env){
    stage("Deploy ${config.program_name}") {
        app_env.deploy_server_instances.each { entry -> 
            def instance = entry.split(" : ")
            echo """deploy to server name: ${instance[0]} with ip: ${instance[1]}"""
        }
    }
}