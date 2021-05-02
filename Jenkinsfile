pipeline {

    agent any
	
    tools { 
       
		maven 'Maven 3.6.3' 
		jdk 'jdk8'

    }
    
    environment {
    
        ENV_IP = '54.217.10.215'
        RDS_DB_URL = 'prod-rds.csqfw1gtm6ou.eu-west-1.rds.amazonaws.com:3306'
        DOCKER_CREDS = credentials('Docker-Creds')
        
    }

    stages {   
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
		            	sh 'sudo docker build --build-arg rds_url=jdbc:mysql://${RDS_DB_URL}/hq -t backend .'
		                sh 'sudo docker run -d -p 9001:9001 backend'
		                
                }
            }
        }
        
        stage('frontend-build-deploy') {
        
            steps {
            
            	echo 'Running frontend on nginx......'
            	
            	dir('./frontend'){ 
            		
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm install'
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm run build'
	                sh 'sudo docker build -t frontend .'
	                sh 'sudo docker run -d -p 80:80 frontend'
	                
                }
            }
        }
        
        stage('push-images-dockerhub') {
            
            steps {
     
                echo 'Login DockerHub and push images......'
         		sh 'sudo docker images'
         		sh 'sudo docker login docker.io -u="${DOCKER_CREDS_USR}" -p="${DOCKER_CREDS_PSW}"'
         		sh 'sudo docker tag frontend vijetaagrawal/frontend'
         		sh 'sudo docker push vijetaagrawal/frontend'
         		sh 'sudo docker tag backend vijetaagrawal/backend'
         		sh 'sudo docker push vijetaagrawal/backend'
         		

            }
        }
    }
}
