libraries {
    git
    dotnet
}

application_environments{
    staging {
        deploy_server_instances = [ "i-07f174dd7dfc7b101 : 10.10.22.8" ]
    }
    production {
        deploy_server_instances = [ "server-name : 0.0.0.0" ]
    }
}