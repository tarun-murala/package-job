pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    environment {
        REVISION = "1.${env.BUILD_NUMBER}"
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "10.53.70.151:2500"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
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
                //snDevOpsChange()
                echo "Building" 
                sh "mvn clean package"
                snDevOpsArtifact("""{"artifacts": [{"name": "${pom.artifactId}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "${env.BUILD_NUMBER}","repositoryName": "${NEXUS_REPOSITORY}"}],"stageName": "build","branchName": "master"}""")
            }
        }

        stage('test') {
            steps {
                snDevOpsStep()
                //snDevOpsChange()
                echo "Unit Test"
                sh "mvn test"
                sleep 5
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' 
                }
          }
        }
        stage("package") {
            steps{
                echo "package"
                // snDevOpsChange()
                snDevOpsPackage(name: "tarun-package", artifactsPayload: """{"artifacts": [{"name": "${pom.artifactId}","version": "1.${env.BUILD_NUMBER}.0","semanticVersion": "${env.BUILD_NUMBER}","repositoryName": "${NEXUS_REPOSITORY}"}],"stageName": "build","branchName": "master"}""")
            }
        }
        stage("deploy") {
            steps{
                echo "deploy"
                snDevOpsChange()
            }
        }
    }

}