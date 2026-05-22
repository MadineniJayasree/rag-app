pipeline {

    agent any

    stages {

        stage('Build Maven') {

            steps {

                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {

            steps {

                bat 'docker build -t rag-app .'
            }
        }

        stage('Deploy Containers') {

            steps {

                bat 'docker-compose up -d'
            }
        }
    }
}