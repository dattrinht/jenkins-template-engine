static class app_static {
    static String base_file_by_time() {
        return """${new Date().format('yyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"""
    }

    static String s3_dir_location() {
        return """s3://cf-sandbox-jenkins-temp/$JOB_NAME/${base_file_by_time()}/"""
    }
}

