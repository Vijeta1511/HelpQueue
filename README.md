# HelpQueue
A spring boot full stack application using rest api in backend and reactJs for frontend.

### Technologies used

- Issue backlog management - Jira
- Agile Methodologies – Scrum
- Back end CRUD Application – Java / Spring Boot
- Front end – React JS, HTML, CSS
- Test coverage of 80% on software (Junit, Mockito)
- Cloud Platform - AWS
- MySQL powered RDS hosted on AWS
- CI/CD Pipeline – Jenkins
- Version Control Software – Git
- VM Setup – Ansible and Terraform
- Containerisation Tools – Docker and Kubernetes


### Project Management Board Invite Link
https://id.atlassian.com/invite/p/jira-software?id=gyqwPu57SBeukq3ig_dgRw


### Video with Project Deployment and Demo

This video shows the deployment of Help Queue application
- From an EC2 on AWS called Setup VM which has terraform, Ansible, aws-cli, eksctl, kubectl installed.
- ssh key from that EC2 in terraform script
- create resources with terraform
- create kubernetes cluster with eksctl
- run ansible playbook for installations on JenkinsVM created with terraform
- make necessary configurations on jenkinsVM
- create kubernetes pods and services
- check connection with nginx-reverse-proxy-lb external-ip address.
 
## Installation

### Frontend

HelpQueue requires [Node.js](https://nodejs.org/) v12+ to run.

Install the dependencies and devDependencies and start the server.

```sh
cd frontend
npm run install
```

### Backend

HelpQueue requires JDK 8 and maven 3.6.3 to run.

Install the dependencies and devDependencies and start the server.

```sh
cd frontend
mvn clean install -DskipTests
```

## Docker

HelpQueue is very easy to install and deploy in a Docker container.

### Frontend - Port 3000

```sh
cd frontend
docker login docker.io -u="${DOCKER_CREDS_USR}" -p="${DOCKER_CREDS_PSW}"
docker build -t frontend .
docker tag frontend <username>/frontend
docker push <username>/frontend
```
### Backend - Port 9001

```sh
cd backend
docker login docker.io -u="${DOCKER_CREDS_USR}" -p="${DOCKER_CREDS_PSW}"
docker build -t backend .
docker tag backend <username>/backend
docker push <username>/backend
```
### Nginx-reverse-proxy - Port 80

```sh
cd reverse-proxy
docker login docker.io -u="${DOCKER_CREDS_USR}" -p="${DOCKER_CREDS_PSW}"
docker build -t reverse-proxy .
docker tag reverse-proxy <username>/reverse-proxy
docker push <username>/reverse-proxy
```

### Authors
Vijeta Agrawal - Vijeta1511

