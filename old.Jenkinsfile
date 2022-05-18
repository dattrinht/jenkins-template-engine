def base_file_by_time = """${new Date().format('yyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"""
def S3_DIR_LOCATION = """s3://cf-sandbox-jenkins-temp/$JOB_NAME/${base_file_by_time}/"""

def config = [:]
config.projects = [:]

config.git_repository_name = [
    "lighthousebasement": "staging-s102",
	"lheditorialapi" : "staging",
]
config.program_names = [
    "ED-Editorial.API",
]

config.projects["ED-Editorial.API"] = [:]
config.projects["ED-Editorial.API"].csproj_file_name = "lheditorialapi/Services/Editorial/Editorial.API/Editorial.API.csproj"
config.projects["ED-Editorial.API"].port = 11001
config.projects["ED-Editorial.API"].supervisor_command = "dotnet Lighthouse.Services.Editorial.API.dll"
config.projects["ED-Editorial.API"].deploy_server_instances = [
    "i-07f174dd7dfc7b101" : "10.10.22.8"
]
 

def getCommandSupervisor(SERVICE_NAME, OBJECT_S3) {
    def command = """
                mkdir -p /etc/supervisor/conf.d/
                aws s3 cp --only-show-errors ${OBJECT_S3} /etc/supervisor/conf.d/${SERVICE_NAME}.conf
                """
    return command
}

def getCommandDeploy(SERVICE_NAME, OBJECT_S3, OUTPUT_NAME) {
    def command = """
                mkdir -p /home/creativeforce/${SERVICE_NAME}
                cd /home/creativeforce
                aws s3 cp --only-show-errors ${OBJECT_S3} ${OUTPUT_NAME}
                
                supervisorctl stop ${SERVICE_NAME}
                rm -rf /home/creativeforce/${SERVICE_NAME}/*
                tar -xf ${OUTPUT_NAME} -C /home/creativeforce/${SERVICE_NAME}
                
                chown -R creativeforce:creativeforce /var/lighthouse
                chown -R creativeforce:creativeforce /home/creativeforce/${SERVICE_NAME}
                
                supervisorctl reread
                supervisorctl update ${SERVICE_NAME}
                supervisorctl start ${SERVICE_NAME}
                rm ${OUTPUT_NAME}
                """
    return command
}

def getSupervisorConfigFile(SERVICE_NAME, COMMAND) {
    def supervisor_config_value = """
        [program:${SERVICE_NAME}]
        command=${COMMAND}
        directory=/home/creativeforce/${SERVICE_NAME}/
        autostart=true
        autorestart=true
        stderr_logfile=/var/log/supervisor/${SERVICE_NAME}.err.log
        stdout_logfile=/var/log/supervisor/${SERVICE_NAME}.out.log
        environment=ASPNETCORE_ENVIRONMENT=${env.ASPNETCORE_ENVIRONMENT}
        user=creativeforce
        group=creativeforce
        stopsignal=INT
    """
    return supervisor_config_value
}

pipeline {
    agent { label 'API-2' }
    
    stages {
        stage('Git') {
            steps {
                
                script {
					config.git_repository_name.each { entry -> 
						sh "mkdir -p ${entry.key}"
						dir("${entry.key}") {
							git branch: "${entry.value}", credentialsId: "bitbucket", poll: false, url: """https://creativeforce-deployment@bitbucket.org/creative-force-io/${entry.key}.git"""
						}
					}
				
                    for (program_name in config.program_names) {
                        
                        def program_output = """${program_name}${base_file_by_time}.tgz"""
                        def s3_object_output = """${S3_DIR_LOCATION}${program_output}"""
                        def project = config.projects[program_name]
                        
                        def supervisor_config_file = """${program_name}${base_file_by_time}.conf"""
                        def s3_object_supervisor_config = """${S3_DIR_LOCATION}${supervisor_config_file}"""
                        def supervisor_config_file_content = getSupervisorConfigFile(program_name, project.supervisor_command)
                        
                        stage("Build ${program_name}") {
                            sh """dotnet restore --verbosity quiet ${project.csproj_file_name}"""
                            sh """dotnet build --verbosity quiet --configuration Release --no-restore ${project.csproj_file_name}"""
                            sh """dotnet publish --verbosity quiet --configuration Release --no-build --output publish/${program_name} ${project.csproj_file_name}"""
                            dir('publish') {
                                sh """tar -czf ${program_output} -C ${program_name} ."""
                                sh """aws s3 cp --only-show-errors ${program_output} ${s3_object_output}"""
                            }
                            
                            writeFile file: supervisor_config_file, text: """${supervisor_config_file_content}"""
                            sh """aws s3 cp --only-show-errors ${supervisor_config_file} ${s3_object_supervisor_config}"""
                        }
                        
                        stage("Deploy ${program_name}") {
                            def commandSupervisor = getCommandSupervisor(program_name, s3_object_supervisor_config)
                            def commandDeploy = getCommandDeploy(program_name, s3_object_output, program_output)
                            
                            project.deploy_server_instances.each { entry -> 
                                sh """JenkinsSendCommand --instance-ids ${entry.key} --comment $JOB_NAME --commands '${commandSupervisor}'"""
                                sh """JenkinsSendCommand --instance-ids ${entry.key} --comment $JOB_NAME --commands '${commandDeploy}'"""
                            }
                        }
                    }
                }   
            }
        }
    }
    
    post {
        cleanup {
            cleanWs()
            sh """aws s3 rm --recursive --only-show-errors ${S3_DIR_LOCATION}"""
        }
    }
}
