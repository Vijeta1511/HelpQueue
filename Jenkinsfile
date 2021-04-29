pipeline {

    agent any
	
    tools { 
       
	maven 'Maven 3.6.3' 
	jdk 'jdk8'

    }
    
    environment {
    
        ENV_IP = '34.244.55.196'
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
        
        stage('install-NodeJS') {
        
            steps {
            	echo 'Installing NodeJS.......'
            	sh 'sudo apt update'
           		sh 'sudo apt install nodejs -y'
           		sh 'sudo apt install npm -y'
            }
        }
    
        stage('backend-test') {
        
            steps {
            
            	script {
            
            	echo 'Running backend test......'
            	
            	dir('./backend/src/main/resources'){
            	
            		def props = "spring.profiles.active = test"
            		
            		writeFile(file: 'application.properties', text: props)
            		
            		}
            		
            		dir('./backend'){   
            		
	           		sh 'mvn test'
	           		
	           		}
           		}
            }
        }
        
        stage('backend-build-run') {
            
            steps {
            	
            	script {
            
                echo 'Running backend build and run......'
            	dir('./backend/src/main/resources'){
            	
            		def props = "spring.profiles.active = prod"
            		
            		writeFile(file: 'application.properties', text: props)
            		
            		}
            		
            		dir('./backend'){
            		
	            	sh 'mvn clean install -DskipTests'
	            	sh 'sudo docker build -t backend-build:1.0.1 .'
	                sh 'sudo docker run -d -p 9001:9001 backend-build:1.0.1'
	                
	                }
	                
                }
            }
        }
        
        stage('frontend build-run') {
        
            steps {
            	echo 'Running frontend on nginx......'
            	
            	dir('./frontend'){ 
            		
            		sh 'set "REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets" && npm run build'
	                sh 'sudo docker build -t react-frontend:1.0.1 .'
	                sh 'sudo docker run -d -p 80:80 react-frontend:1.0.1'
	                
                }
            }
        }
    }
}
