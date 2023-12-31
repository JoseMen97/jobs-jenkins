pipelineJob('proyect_liquibase/liquibase_dsl') {
    parameters {
        stringParam('rama', '', 'rama de ejecucion')
        stringParam('schema', '', 'rama de ejecucion')
    }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/JoseMen97/jobs-jenkins.git')
                    }
                    branch('master')
                }
                scriptPath('jobs/goya/jenkinsFile.groovy')
            }
        }
    }
}
