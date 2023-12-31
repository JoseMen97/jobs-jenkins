pipelineJob('ant_multi/ant_multi_dsl') {
    displayName('ant_multi_dsl')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/JoseMen97/jobs-jenkins.git')
                    }
                    branch('master')
                }
                scriptPath('jobs/ant_multi/jenkinsFile.groovy')
            }
        }
    }
    logRotator{
        numToKeep(10)
    }
    properties {
        pipelineTriggers{
            triggers{
                genericTrigger{
                    genericRequestVariables{
                        genericRequestVariable{
                            key("payload")
                            regexpFilter("\$payload")
                        }
                    }
                    token('ant')
                    regexpFilterText("")
                    regexpFilterExpression("")
                }
            }
        }
    }
    parameters {
        string {
            name('rama')
            defaultValue('')
            description('rama')
        }
    }
}
