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
            		
            			sh 'export DATABASE_URL=jdbc:mysql://prod-rds.csqfw1gtm6ou.eu-west-1.rds.amazonaws.com:3306/hq'
		            	sh 'mvn clean install -DskipTests'
		            	sh 'sudo docker build -t backend .'
		                sh 'sudo docker run -d -p 9001:9001 backend'
		                
                }
            }
        }
        
        stage('frontend-build-dockerize') {
        
            steps {
            
            	echo 'Building frontend and running the image on this machine.......'
            	
            	dir('./frontend'){ 
            		
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm install'
            		sh 'REACT_APP_BASE_URL=http://${ENV_IP}:9001/api/v1/tickets npm run build'
	                sh 'sudo docker build -t frontend .'
	                sh 'sudo docker run -d -p 80:80 frontend'
	                
                }
            }
        }
        
        stage('nginx-reverseProxy-dockerize') {
            
            steps {
     
                echo 'Building nginx reverse proxy image......'
            		
            		dir('./reverse-proxy'){
            	
		            	sh 'sudo docker build -t reverse-proxy .
		                
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
        
        stage('eks-cluster-details') {
            
            steps {
     
                echo 'Adding cluster config......'
         		sh 'sudo aws eks --region eu-west-1 update-kubeconfig --name DemoCluster'
         		sh 'sudo eksctl get cluster --name DemoCluster'
         		sh 'sudo kubectl get nodes'
         		
            }
        }
        
        stage('backend-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for backend......'
                
                dir('./kubernetes'){ 
                
         		sh 'sudo kubectl apply -f backend-deploy.yaml'
         		sh 'sudo kubectl apply -f backend-lb.yaml'
         		sh 'sudo kubectl describe service backend-lb'
         		sh 'sudo kubectl get pods -o wide'
         		
         		
         		}
            }
        }
        
        stage('frontend-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for frontend......'
                
                dir('./kubernetes'){ 
                
         		sh 'sudo kubectl apply -f frontend-deploy.yaml'
         		sh 'sudo kubectl apply -f frontend-lb.yaml'
         		sh 'sudo kubectl describe service frontend-lb'
         		sh 'sudo kubectl get pods -o wide'
         		
         		}
            }
        }
        
        stage('nginx-reverse-proxy-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for nginx-reverse-proxy......'
                
                dir('./kubernetes'){ 
                
         		sh 'sudo kubectl apply -f nginx-reverse-proxy-deploy.yaml'
         		sh 'sudo kubectl apply -f nginx-reverse-proxy-lb.yaml'
         		sh 'sudo kubectl describe service nginx-reverse-proxy-lb'
         		sh 'sudo kubectl get pods -o wide'
         		
         		}
            }
        }
    }
}
