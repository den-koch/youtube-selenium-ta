pipeline{
    agent label 'agent1'

    parameters{
        choice(name: 'ENV', choices: ['dev','qa'], description: '')
        choice(name: 'BROWSER', choices: ['edge','chrome','firefox'], description: '')
        choice(name: 'SUITE', choices: ['testng_smoke.xml','testng_regression.xml'], description: '')
    }

    stages{
        stage ('Test'){
            sh 'mvn clean test -Denv=${params.ENV} -Dbrowser=${params.BROWSER} -DxmlFilePath="src/test/resources/${params.SUITE}"'
        }
    }
}