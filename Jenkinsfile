pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        NEXUS_REPOSITORY = "maven-releases"
        ARTIFACT_ID = "tarun-artifact"
        SN_CREDS = credentials('sn-creds')
    }
    stages {
        stage("checkout") {
            steps {
                snDevOpsStep()
                snDevOpsChange()
                echo "Building" 
                checkout scm
            }
        }

        stage("build") {
            steps {
                snDevOpsStep()
                snDevOpsChange()
                echo "Building" 
                //withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn clean install -DskipTests=true'
                //}
                // artifact register - semantic version, stage name and branch are optional
                //snDevOpsArtifact(artifactsPayload:"""{"artifacts": [{"name": "${ARTIFACT_ID}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "1.${env.BUILD_NUMBER}.0","repositoryName": "${NEXUS_REPOSITORY}"}]}""")  
                
            }
        }

        stage('test') {
            steps {
                snDevOpsStep()
                snDevOpsChange()
                echo "Unit Test"
                //withMaven(maven : 'apache-maven-3.6.1') {
                 bat 'mvn clean test'
                //}
                sleep 5
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' 
                    bat """
                    curl --header "Content-Type: application/json" \
                        --request POST \
                        --data "{'number':'${BUILD_NUMBER}','url':'${BUILD_URL}','name':'${JOB_NAME}','stage':'test'}" \
                        -L "https://$SN_CREDS_USR:$SN_CREDS_PSW@devopsfixvalidations.service-now.com/api/sn_devops/v1/devops/tool/test?toolId=a0c164d8db7dd0104b6a9e7aca9619fd&testType=Selenium"
                    """
                }
          }
        }

        stage("deploy") {
            steps{
                snDevOpsStep()                    
                //snDevOpsPackage(name: "tarun-package-1.${env.BUILD_ID}.0", artifactsPayload: """{"artifacts": [{"name": "${ARTIFACT_ID}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "1.${env.BUILD_NUMBER}.0","repositoryName": "${NEXUS_REPOSITORY}"}]}""")
                snDevOpsChange()
                echo "deploy"
            }
        }
    }

}
