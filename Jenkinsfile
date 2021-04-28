pipeline {

    agent any
	
    tools { 
       
	maven 'Maven 3.6.3' 

    }

    stages {
    	
    	stage('install-docker') {
        
            steps {
            	echo 'Installing docker.......'
            	sh 'sudo apt-get update'
           		sh 'curl https://get.docker.com | sudo bash'
           		sh 'sudo chown ubuntu /var/run/docker.sock'
            }
        }
    
        stage('backend-test') {
        
            steps {
            	echo 'Running backend test......'
            	sh 'cd ./backend'
            	sh 'pwd'
            	dir('./backend'){
           		sh 'mvn test'
           		}
            }
        }
        
        stage('backend-build-run') {
            
            steps {
            
                echo 'Running backend build and run......'
            	sh 'cd ./backend'          	
            	sh 'mvn clean install -DskipTests'
            	sh 'docker build -t backend-build:1.0.1 .'
                sh 'docker run -d -p 9001:9001 backend-build:1.0.1'
                
            }
        }
        
        stage('frontend run') {
        
            steps {
            	echo 'Running frontend on nginx......'
            	sh 'cd ./frontend' 
                sh 'docker build -t react-frontend:1.0.1 .'
                sh 'docker run -d -p 80:80 react-frontend:1.0.1'
                
            }
        }
    }
}
