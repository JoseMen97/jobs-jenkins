node ('database'){
    container('liquibase'){
        withCredentials([usernamePassword(credentialsId: 'user_liquibase', passwordVariable: 'pass', usernameVariable: 'user')]) {
            stage('checkout'){
                checkout(
                    [$class: 'GitSCM', branches: [[name: '*/'+env.rama]],
                    userRemoteConfigs: [[url: 'https://github.com/JoseMen97/liquibase.git']]]
                )
                list = sh (script: 'ls scripts', returnStdout: true).split('\n')
                linea = 12
                id = 1
                sh "mkdir changeLogs"
                list.each{ sql ->
                    sqlXml = sql.replace(".sql",".xml")
                    sh "cp changelog_base.xml changeLogs/${sqlXml}"
                    sh """
                        sed -i '${linea} s/^/<include file="changeLogs\\/${sqlXml}"\\/>/' changelog.xml
                    """
                    sh """
                        sed -i 's/#id/${id}/' changeLogs/${sqlXml}
                        sed -i 's/#autor/${sql}-feature/' changeLogs/${sqlXml}
                        sed -i 's/#sql/..\\/scripts\\/${sql}/' changeLogs/${sqlXml}
                    """
                    linea += 1
                    id += 1
                }

            }
    	    stage('validate-BD'){
                sh "liquibase validate --username=${user} --password=${pass}"
                sh "liquibase status --username=${user} --password=${pass}"
            }
    	    stage('excute-BD'){
                sh "liquibase update-sql --username=${user} --password=${pass}"
                sh "liquibase update --username=${user} --password=${pass}"	
            }
        }
    }
}
