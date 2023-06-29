pipeline{
    agent { label 'monoliticas'}

    stages {
        stage ('checkout') {
            container('ant'){
                steps{
                    checkout(
                        [$class: 'GitSCM', branches: [[name: '*/'+env.rama]],
                        userRemoteConfigs: [[url: 'https://github.com/JoseMen97/ant.git']]]
                    )
                }
            }
        }
        stage ('Build Ant'){
            steps{
                container('ant'){
                    sh """
                        ant compile
                    """
                }
            }
        }
        stage ('Run Ant'){
            steps{
                container('ant'){
                    sh """                    
                        ant run
                        ant clean
                    """
                }
            }
        }
    }
}
