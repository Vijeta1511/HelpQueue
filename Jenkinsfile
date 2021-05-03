pipeline {

    agent any
	
    tools { 
       
		maven 'Maven 3.6.3' 

    }
    
    environment {
    
        DOCKER_CREDS = credentials('Docker-Creds')
        
    }
    
    stages {  
     
        stage('installations') {
        
            steps {

            	echo 'Installing eksctl......'
            	sh 'sudo curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp'
            	sh 'sudo mv /tmp/eksctl /usr/local/bin'
            	sh 'sudo eksctl version'
            	
            	echo 'Installing kubectl......'
            	sh 'sudo curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.19.6/2021-01-05/bin/linux/amd64/kubectl'
            	sh 'sudo chmod +x kubectl'
            	sh 'sudo mv kubectl /usr/local/bin'
            	sh 'kubectl version'
            	sh 'kubectl get nodes'
              }
             		
           }
            
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
		                sh 'sudo docker run -d -p 9001:9001 backend'
		                
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
	                sh 'sudo docker run -d -p 80:80 frontend'
	                
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
        
        stage('eks-cluster-details') {
            
            steps {
     
                echo 'Adding cluster config......'
         		sh 'sudo aws eks --region eu-west-1 update-kubeconfig --name HelpQueueCluster'
         		sh 'sudo eksctl get cluster --name HelpQueueCluster'
         		sh 'sudo kubectl get nodes'
         		
            }
        }
        
        stage('backend-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for backend......'
                
                dir('./kubernetes'){ 
                
         		sh 'sudo kubectl apply -f backend-deploy.yaml'
         		sh 'sudo kubectl apply -f backend-cip.yaml'
         		sh 'sudo kubectl describe service backend-cip'
         		sh 'sudo kubectl get pods -o wide'
         		
         		
         		}
            }
        }
        
        stage('frontend-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for frontend......'
                
                dir('./kubernetes'){ 
                
         		sh 'sudo kubectl apply -f frontend-deploy.yaml'
         		sh 'sudo kubectl apply -f frontend-cip.yaml'
         		sh 'sudo kubectl describe service frontend-cip'
         		sh 'sudo kubectl get pods -o wide'
         		
         		}
            }
        }
        
        stage('nginx-reverse-proxy-deploy') {
            
            steps {
     
                echo 'Creating kubernetes pods and load-balancer for nginx-reverse-proxy......'
                
                dir('./kubernetes') { 
                 
         		sh 'sudo kubectl apply -f nginx-reverse-proxy-deploy.yaml'
         		sh 'sudo kubectl apply -f nginx-reverse-proxy-lb.yaml'
         		sh 'sudo kubectl describe service nginx-reverse-proxy-lb'
         		sh 'sudo kubectl get pods -o wide'
         		}
         	}
         }
      }
  }