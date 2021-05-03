pipeline {

    agent any
	
    tools { 
       
		maven 'Maven 3.6.3' 

    }
    
    environment {
    
        DOCKER_CREDS = credentials('Docker-Creds')
        
    }
    
    stages {  
            
        stage('backend-test') {
        
            steps {

            	echo 'Running all tests for backend......'
            		
            		dir('./backend'){   
            		
	           			sh 'mvn clean install -Ptest'

           		}
            }
        }
        
        stage('backend-build-dockerize') {
            
            steps {
     
                echo 'Building backend and running the image on this machine......'
            		
            		dir('./backend'){
            		
		            	sh 'mvn clean install -DskipTests'
		            	sh 'sudo docker build -t backend .'
		                
                }
            }
        }
        
        stage('frontend-build-dockerize') {
        
            steps {
            
            	echo 'Building frontend and running the image on this machine.......'
            	
            	dir('./frontend'){ 
            		
            		sh 'npm install'
            		sh 'npm run build'
	                sh 'sudo docker build -t frontend .'
	                
                }
            }
        }
        
        stage('nginx-reverseProxy-dockerize') {
            
            steps {
     
                echo 'Building nginx reverse proxy image......'
            		
            		dir('./reverse-proxy'){
            	
		            	sh 'sudo docker build -t reverse-proxy .'
		                
                }
            }
        }
        
        stage('tagging-docker-images') {
            
            steps {
     
                echo 'Tagging frontend and backend images......'
         		sh 'sudo docker images'
         		sh 'sudo docker login docker.io -u="${DOCKER_CREDS_USR}" -p="${DOCKER_CREDS_PSW}"'
         		sh 'sudo docker tag frontend vijetaagrawal/frontend'
         		sh 'sudo docker tag backend vijetaagrawal/backend'
         		sh 'sudo docker tag reverse-proxy vijetaagrawal/reverse-proxy'

            }
        }
        
        stage('push-images-dockerhub') {
            
            steps {
     
                echo 'Pushing frontend and backend images to dockerhub......'
         		sh 'sudo docker push vijetaagrawal/frontend'
         		sh 'sudo docker push vijetaagrawal/backend'
         		sh 'sudo docker push vijetaagrawal/reverse-proxy'
            }
        }
        
      }
  }