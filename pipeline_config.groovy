jte {
    pipeline_template = "Template.Jenkinsfile"
}

libraries {
    git
    dotnet {
        service_name = "Editorial-API"
        service_port = 11001
        csproj_file_path = "lheditorialapi/Services/Editorial/Editorial.API/Editorial.API.csproj"
        supervisor_command = "dotnet Lighthouse.Services.Editorial.API.dll"
        timestamp = """${new Date().format('yyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"""
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