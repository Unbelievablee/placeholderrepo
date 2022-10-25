#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'git@github.com:Unbelievablee/jenkins-shared-library.git',
    credentialsId: 'Ivan_github_ssh_key']
)

def gv

pipeline {
    agent any
    tools {
        maven 'maven 3.8.6'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    buildImage '127.0.0.1:8083/java-maven-app:2.5'
                    dockerLogin()
                    dockerPush '127.0.0.1:8083/java-maven-app:2.5'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
