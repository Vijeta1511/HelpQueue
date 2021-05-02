pipeline {

    agent any
	
    tools { 
       
		maven 'Maven 3.6.3' 
		jdk 'jdk8'

    }
    
    environment {
    
        ENV_IP = '34.244.55.196'
        RDS_DB_URL = 'jdbc:mysql://vijeta-db-3.csqfw1gtm6ou.eu-west-1.rds.amazonaws.com:3306/hq'

        
    }

    stages {
    	
    	stage('install-docker') {
        
            steps {
            
            	echo 'Installing docker.......'
            	sh 'sudo apt-get update'
           		sh 'curl https://get.docker.com | sudo bash'
           		sh 'sudo chown ubuntu /var/run/docker.sock'
           		sh 'sudo docker stop $(sudo docker ps -a -q)'
           		sh 'sudo docker system prune -af'
            }
        }
        
        stage('install-NodeJS') {
        
            steps {
            
            	echo 'Installing NodeJS.......'
            	sh 'curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -'
           		sh 'sudo apt install nodejs -y'
           		sh 'node --version'
           		sh 'npm --version'
            }
        }
    
        stage('backend-test') {
        
            steps {

            	echo 'Running backend test......'
            		
            		dir('./backend'){   
            		
	           			sh 'mvn clean install -Ptest'

           		}
            }
        }
        
        stage('backend-build-deploy') {
            
            steps {
     
                echo 'Running backend build and run......'
            		
            		dir('./backend'){
            		
		            	sh 'mvn clean install -DskipTests'
		            	sh 'sudo docker build -t backend-build:1.0.1 .'
		                sh 'sudo docker run -d -p 9001:9001 backend-build:1.0.1'
		                
                }
            }
        }
        
        stage('frontend-build-deploy') {
        
            steps {
            
            	echo 'Running frontend on nginx......'
            	
            	dir('./frontend'){ 
            		
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm install'
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm run build'
	                sh 'sudo docker build -t react-frontend:1.0.1 .'
	                sh 'sudo docker run -d -p 80:80 react-frontend:1.0.1'
	                
                }
            }
        }
    }
}
