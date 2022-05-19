String base_file_by_time() {
    return """${new Date().format('yyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"""
}

String s3_dir_location() {
    return """s3://cf-sandbox-jenkins-temp/$JOB_NAME/${base_file_by_time()}/"""
}

String buildCommandSupervisor(String OBJECT_S3, String SERVICE_NAME) {
    def command = """
            mkdir -p /etc/supervisor/conf.d/
            aws s3 cp --only-show-errors ${OBJECT_S3} /etc/supervisor/conf.d/${SERVICE_NAME}.conf
        """
    return command
}

String buildCommandDeploy(String SERVICE_NAME, String OBJECT_S3, String OUTPUT_NAME) {
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

String buildSupervisorConfigFile(String SERVICE_NAME, String COMMAND) {
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