pipeline{
    agent {
        label 'docker-agent'
    }

    parameters{
        choice(name: 'ENV', choices: ['dev','qa'], description: '')
        choice(name: 'BROWSER', choices: ['chrome_remote','firefox_remote', 'edge_remote'], description: '')
        choice(name: 'SUITE', choices: ['testng_smoke.xml','testng_regression.xml'], description: '')
    }

    stages{
        stage ('Test'){
            steps{
                sh '''mvn clean test \
                    -Denv=${ENV} \
                    -Dbrowser=${BROWSER} \
                    -DxmlFilePath="src/test/resources/${SUITE}"
                '''
            }
        }
    }
}