//aws_key_pair
variable "akp_key_name" {
  default = "terraformKey"
}

//VPC
variable "vpc_cidr_block" {
  default = "11.0.0.0/16"
}

//Internet Gateway
variable "ig_name" {
  default = "main_ig"
}

//Subnet A
variable "subA_name" {
  default = "publicSubnetA"
}

variable "subA_cidr" {
  default = "11.0.1.0/24"
}

variable "subA_az" {
  default = "eu-west-1a"
}

//Subnet B
variable "subB_name" {
  default = "publicSubnetB"
}

variable "subB_cidr" {
  default = "11.0.2.0/24"
}

variable "subB_az" {
  default = "eu-west-1b"
}

//Subnet C
variable "subC_name" {
  default = "publicSubnetC"
}

variable "subC_cidr" {
  default = "11.0.3.0/24"
}

variable "subC_az" {
  default = "eu-west-1c"
}

//Route Table
variable "rt_name" {
  default = "main_rt"
}

variable "rt_cidr" {
  default = "0.0.0.0/0"
}

variable "rt_ipv6_cidr" {
  default = "::/0"
}

//Security Group for EC2
variable "jenkins_sg_name" {
  default = "JenkinsEC2_sg"
}

variable "jenkins_sg_description" {
  default = "Allow ssh, http and custom tcp for ports used in the application."
}

variable "sg_cidr_blocks" {
  default = "0.0.0.0/0"
}

variable "sg_ipv6_cidr_blocks" {
  default = "::/0"
}

//EC2 JenkinsEC2
variable "jenkinsEc2_ami_id" {
  default = "ami-06fd78dc2f0b69910"
}

variable "jenkinsEc2_name" {
  default = "JenkinsEC2"
}

variable "jenkinsEc2_instance_type" {
  default = "t2.medium"
}

//Test EC2
variable "testEc2_name" {
  default = "TestEC2"
}

//RDS Security Group
variable "rds_sg_name" {
  default = "rds_sg"
}

variable "rds_sg_description" {
  default = "Allow MYSQL/Aurora inbound traffic"
}

//Subnet Group
variable "subGroup_name" {
  default = "prod_rds_subnet_group"
}

//RDS
variable "rds_identifier" {
  default = "prod-rds"
}

variable "rds_engine" {
  default = "mysql"
}

variable "rds_engine_version" {
  default = "8.0.23"
}

variable "rds_db_name" {
  default = "hq"
}

variable "rds_instance_class" {
  default = "db.t2.micro"
}

variable "rds_username" {
  default = "admin"
}

variable "rds_password" {
  default = "Msspl#109"
}

variable "rds_pgn" {
  default = "default.mysql8.0"
}
