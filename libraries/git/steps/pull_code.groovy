void call(Map config){
    stage("Git") {
        script {
            config.git_repositories.split(", ").each { item ->
                def repo = item.split(" : ")
                echo """Pull code from repo: ${repo[0]} on branch: ${repo[1]}"""
            }
        }
    }
}