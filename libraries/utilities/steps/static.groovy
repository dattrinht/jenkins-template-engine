String base_file_by_time() {
    return """${new Date().format('yyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"""
}

String s3_dir_location() {
    def base_file = base_file_by_time()
    return """s3://cf-sandbox-jenkins-temp/$JOB_NAME/${base_file}/"""
}