void call(Map config){
    stage("Deploy ${config.program_name}") {
        config.deploy_server_instances.split(", ").each { entry -> 
            def instance = entry.split(" : ")
            echo """deploy to server name: ${instance[0]} with ip: ${instance[1]}"""
        }
    }
}