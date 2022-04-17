pipeline {
    agent any

    stages {
        stage('Stage MedicareWeb') {
            steps {
               
               echo 'Getting code from github'
                  // Get some code from a GitHub repository
               git 'https://github.com/bvisvanathan/AutomationCapstoneProject.git'

                // Run Maven on a Unix agent.
                
               
            }
        }
        stage('Test MedicareWeb') {
            steps {
                echo 'Testing..'
                bat "mvn test"
            }
        }
        stage('Test Reqres API') {
            steps {
                echo 'Testing API....'
                bat """
                cd C:\\Users\\balaj\\AppData\\Roaming\\npm 
                newman run https://www.getpostman.com/collections/add0d9647a3632b08889
                """
                
            }
        }
    }
}