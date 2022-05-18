void check_out(app_env){
    stage("Git") {
        script {
            app_env.git_repositories.each { item ->
                def repo = item.split(" : ")
                def repo_name = repo[0]
                def branch_name = repo[1]

                echo "mkdir -p ${repo_name}"
                echo """
                    git branch: "${branch_name}", credentialsId: "bitbucket", poll: false, url: "https://creativeforce-deployment@bitbucket.org/creative-force-io/${repo_name}.git"
                """
            }
        }
    }
}