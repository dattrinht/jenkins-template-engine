
def service_name = "API from def"

libraries {
    dotnet_deploy {
        nginx {
            command = "deploy nginx"
            service_name = "API"
        }
        aws_gateway {
            command = "deploy aws_gateway"
            service_name = service_name
        }
    }
}

application_environments{
    staging {
        deploy_server_instances = [ "i-07f174dd7dfc7b101": "10.10.22.8" ]
        git_repositories = [ "lheditorialapi": "staging", "lighthousebasement": "staging-s103" ]
    }
    production {
        deploy_server_instances = [ "server-name": "0.0.0.0" ]
        git_repositories = [ "lheditorialapi": "master", "lighthousebasement": "master" ]
    }
}