pipeline {
    agent any

    environment {
	firstvar = credentials('JenkinsCreds')
	}
    stages {
        stage('Build') {
            steps {
               sh 'echo "Hello World"'
	       sh 'echo $firstvar_PSW' 
	       sh 'echo $firstvar'
            }
        }
        stage('Test') {
            steps {
                sh 'echo "Hello World"'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Hello World"'
            }
        }
    }
}
