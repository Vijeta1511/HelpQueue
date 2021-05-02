pipeline {

    agent any
	
    tools { 
       
		maven 'Maven 3.6.3'

    }
    
    environment {
    
        ENV_IP = '54.75.42.223'
        RDS_DB_URL = 'jdbc:mysql://vijeta-db-3.csqfw1gtm6ou.eu-west-1.rds.amazonaws.com:3306/hq'
        
    }

    stages {
    	
    	stage('docker-configs') {
        
            steps {
            
            	echo 'changing file permissions and removing old images.......'
            	sh 'sudo apt-get update'
           		sh 'curl https://get.docker.com | sudo bash'
           		sh 'sudo chown ubuntu /var/run/docker.sock'
           		sh 'sudo docker stop $(sudo docker ps -a -q)'
           		sh 'sudo docker system prune -af'
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
		            	sh 'sudo docker build --build-arg rds_url=jdbc:mysql://${RDS_DB_URL}/hq -t backend-build:1.0.1 ./backend'
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
	                sh 'sudo docker build -t react-frontend:1.0.1 ./frontend'
	                sh 'sudo docker run -d -p 80:80 react-frontend:1.0.1'
	                
                }
            }
        }
        
        stage('push-images-dockerhub') {
            
            steps {
     
                echo 'Login DockerHub and push images......'
         		sh 'sudo docker images'

            }
        }
    }
}
