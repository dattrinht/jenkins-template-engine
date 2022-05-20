void check_out(app_env){
    stage("Git") {
        script {
            app_env.git_repositories.each { entry ->
                def repo_name = entry.key
                def branch_name = entry.value

                echo "from git2"
                // echo """
                //     git branch: "${branch_name}", credentialsId: "bitbucket", poll: false, url: "https://creativeforce-deployment@bitbucket.org/creative-force-io/${repo_name}.git"
                // """
            }
        }
    }
}