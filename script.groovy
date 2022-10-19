def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t 127.0.0.1:8083/java-maven-app:2.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin 127.0.0.1:8083"
        sh 'docker push 127.0.0.1:8083/java-maven-app:2.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
