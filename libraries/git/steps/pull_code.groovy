void call(Map config){
    script {
        env.git_repositories.split(", ").each { item ->
            def repo = item.split(" : ")
            echo """Pull code from repo: ${repo[0]} on branch: ${repo[1]}"""
            echo """${config.name}"""
        }
    }
}