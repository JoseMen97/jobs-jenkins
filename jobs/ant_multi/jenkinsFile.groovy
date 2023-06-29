pipeline{
    agent { label 'monoliticas'}

    stages {
        container('ant'){
            stage ('checkout') {
                checkout(
                    [$class: 'GitSCM', branches: [[name: '*/'+env.rama]],
                    userRemoteConfigs: [[url: 'https://github.com/JoseMen97/ant.git']]]
                )
            }
            stage ('Build Ant'){
                sh """
                    ant compile
                """
            }
            stage ('Run Ant'){
                sh """                    
                    ant run
                    ant clean
                """
            }
        }
    }
}
