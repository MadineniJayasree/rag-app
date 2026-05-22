pipeline {

    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21'
        }
    }

    stages {

        stage('Build Maven') {

            steps {

                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {

            steps {

                sh 'docker build -t rag-app .'
            }
        }

        stage('Deploy Containers') {

            steps {

                sh 'docker-compose up -d'
            }
        }
    }
}