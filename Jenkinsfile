pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        NEXUS_REPOSITORY = "maven-releases"
        ARTIFACT_ID = "tarun-artifact"
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
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn clean install'
                }
                // artifact register - semantic version, stage name and branch are optional
                snDevOpsArtifact(artifactsPayload:"""{"artifacts": [{"name": "${ARTIFACT_ID}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "1.${env.BUILD_NUMBER}.0","repositoryName": "${NEXUS_REPOSITORY}"}]}""")  
                
            }
        }

        stage('test') {
            steps {
                snDevOpsStep()
                snDevOpsChange()
                echo "Unit Test"
                withMaven(maven : 'apache-maven-3.6.1') {
                 bat 'test'
                }
                sleep 5
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' 
                }
          }
        }

        stage("deploy") {
            steps{
                snDevOpsStep()                    
                snDevOpsPackage(name: "tarun-package-1.${env.BUILD_ID}.0", artifactsPayload: """{"artifacts": [{"name": "${ARTIFACT_ID}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "1.${env.BUILD_NUMBER}.0","repositoryName": "${NEXUS_REPOSITORY}"}]}""")
                snDevOpsChange()
                echo "deploy"
            }
        }
    }

}
