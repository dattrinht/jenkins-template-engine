def buildCommandSupervisor(Map config) {
    def command = """
            mkdir -p /etc/supervisor/conf.d/
            aws s3 cp --only-show-errors ${config.OBJECT_S3} /etc/supervisor/conf.d/${config.SERVICE_NAME}.conf
        """
    return command
}

def buildCommandDeploy(Map config) {
    def command = """
            mkdir -p /home/creativeforce/${config.SERVICE_NAME}
            cd /home/creativeforce
            aws s3 cp --only-show-errors ${config.OBJECT_S3} ${config.OUTPUT_NAME}
            
            supervisorctl stop ${config.SERVICE_NAME}
            rm -rf /home/creativeforce/${config.SERVICE_NAME}/*
            tar -xf ${config.OUTPUT_NAME} -C /home/creativeforce/${config.SERVICE_NAME}
            
            chown -R creativeforce:creativeforce /var/lighthouse
            chown -R creativeforce:creativeforce /home/creativeforce/${config.SERVICE_NAME}
            
            supervisorctl reread
            supervisorctl update ${config.SERVICE_NAME}
            supervisorctl start ${config.SERVICE_NAME}
            rm ${config.OUTPUT_NAME}
        """
    return command
}

def buildSupervisorConfigFile(Map config) {
    def supervisor_config_value = """
            [program:${config.SERVICE_NAME}]
            command=${config.COMMAND}
            directory=/home/creativeforce/${config.SERVICE_NAME}/
            autostart=true
            autorestart=true
            stderr_logfile=/var/log/supervisor/${config.SERVICE_NAME}.err.log
            stdout_logfile=/var/log/supervisor/${config.SERVICE_NAME}.out.log
            environment=ASPNETCORE_ENVIRONMENT=${env.ASPNETCORE_ENVIRONMENT}
            user=creativeforce
            group=creativeforce
            stopsignal=INT
    """
    return supervisor_config_value
}