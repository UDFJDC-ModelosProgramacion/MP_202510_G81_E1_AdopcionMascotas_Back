pipeline { 
   agent any 
   environment {
      GIT_REPO = 'MP_202510_G81_E1_AdopcionMascotas_Back'
      GIT_CREDENTIAL_ID = '7c21addc-0cbf-4f2e-9bd8-eced479c56c6'
      SONARQUBE_URL = 'http://172.24.101.209:8082/sonar-isis2603'
      SONAR_TOKEN = credentials('sonar-login')
   }
   stages { 
      stage('Checkout') { 
         steps {
            scmSkip(deleteBuild: true, skipPattern:'.*\\[ci-skip\\].*')

            git branch: 'main', 
               credentialsId: env.GIT_CREDENTIAL_ID,
               url: 'https://github.com/UDFJDC-ModelosProgramacion/' + env.GIT_REPO
         }
      }
      stage('Build') {
         // Build artifacts
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v $HOME/.m2:/root/.m2:z -u root') {
                  sh '''
                     java -version
                     mvn clean install
                  '''
               }
            }
         }
      }
      stage('Testing') {
         // Run unit tests
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v $HOME/.m2:/root/.m2:z -u root') {                  
                  sh '''
                     mvn test
                  '''
               }
            }
         }
      }
      stage('Static Analysis') {
         // Run static analysis
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v $HOME/.m2:/root/.m2:z -u root') {
                  sh '''
                     mvn sonar:sonar -Dsonar.token=${SONAR_TOKEN} -Dsonar.host.url=${SONARQUBE_URL}
                  '''
               }
            }
         }
      }      
   }
}
