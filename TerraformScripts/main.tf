provider "aws" {
  shared_credentials_file = "~/.aws/credentials"
  region                  = "eu-west-1"
}

// Creating a aws_key_pair to use with resources
resource "aws_key_pair" "terraformKey" {
  key_name   = var.akp_key_name
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCr7AKMiCxsUKqC4KInttOp7uf0oWU8+ATb9ozIZczu23ZAx0TLwUgXPmW6PX8QV5Yb1QogGWAJ0jHzuGLdS9WpjNBKucw/iVkLNQ+L+dJl5wC4RwXDTLIbQfZIL21OjEHZ9/J60ThIF+cWI9I4LWS30iHYcjsutVkWyQX7SvkpgEz2Q6siedbE7DolWPvVW65sCfooZSzyQXi7dqRMNd6iFZR4u+Ad5hrdQ1x4fx6sbhS/nLgkZEzo2FVrbzFo5zBhqpIInNHsLsvajTva9DHrK9sceMLSD1WN8AtTGcmMMgxW712TYOzsM8dBalCr5ANSXKxDVf6Jv15+l62u1pkz ubuntu@ip-172-31-8-110"
}

// Creating a VPC to wrap all resources
resource "aws_vpc" "main_vpc" {
  cidr_block = var.vpc_cidr_block
  enable_dns_hostnames = true

  tags = {
    Name   = "main_vpc",
    Cohort = 3
  }
}

//Creating an Internet Gateway
resource "aws_internet_gateway" "main_ig" {
  vpc_id = aws_vpc.main_vpc.id

  tags = {
    Name   = var.ig_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

//Creating 3 subnets
resource "aws_subnet" "publicSubnetA" {
  vpc_id                  = aws_vpc.main_vpc.id
  cidr_block              = var.subA_cidr
  availability_zone       = var.subA_az
  map_public_ip_on_launch = true

  tags = {
    Name   = var.subA_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

resource "aws_subnet" "publicSubnetB" {
  vpc_id                  = aws_vpc.main_vpc.id
  cidr_block              = var.subB_cidr
  availability_zone       = var.subB_az
  map_public_ip_on_launch = true

  tags = {
    Name   = var.subB_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

resource "aws_subnet" "publicSubnetC" {
  vpc_id                  = aws_vpc.main_vpc.id
  cidr_block              = var.subC_cidr
  availability_zone       = var.subC_az
  map_public_ip_on_launch = true

  tags = {
    Name   = var.subC_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

//Creating a Route-Table
resource "aws_route_table" "main_rt" {
  vpc_id = aws_vpc.main_vpc.id

  route {
    cidr_block = var.rt_cidr
    gateway_id = aws_internet_gateway.main_ig.id
  }

  route {
    ipv6_cidr_block = var.rt_ipv6_cidr
    gateway_id      = aws_internet_gateway.main_ig.id
  }

  tags = {
    Name   = var.rt_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc,
    aws_internet_gateway.main_ig
  ]
}

//Associating subnets to the route table
resource "aws_route_table_association" "SubA" {
  subnet_id      = aws_subnet.publicSubnetA.id
  route_table_id = aws_route_table.main_rt.id

  depends_on = [
    aws_subnet.publicSubnetA,
    aws_route_table.main_rt
  ]
}
resource "aws_route_table_association" "SubB" {
  subnet_id      = aws_subnet.publicSubnetB.id
  route_table_id = aws_route_table.main_rt.id

  depends_on = [
    aws_subnet.publicSubnetB,
    aws_route_table.main_rt
  ]
}
resource "aws_route_table_association" "SubC" {
  subnet_id      = aws_subnet.publicSubnetC.id
  route_table_id = aws_route_table.main_rt.id

  depends_on = [
    aws_subnet.publicSubnetC,
    aws_route_table.main_rt
  ]
}

//Creating a Security Group for EC2
resource "aws_security_group" "JenkinsEC2_sg" {
  name        = var.jenkins_sg_name
  description = var.jenkins_sg_description
  vpc_id      = aws_vpc.main_vpc.id

  ingress {
    description      = "HTTP from anywhere"
    from_port        = 80
    to_port          = 80
    protocol         = "tcp"
    cidr_blocks      = [var.sg_cidr_blocks]
    ipv6_cidr_blocks = [var.sg_ipv6_cidr_blocks]
  }

  ingress {
    description      = "Nginx port"
    from_port        = 8080
    to_port          = 8080
    protocol         = "tcp"
    cidr_blocks      = [var.sg_cidr_blocks]
    ipv6_cidr_blocks = [var.sg_ipv6_cidr_blocks]
  }

  ingress {
    description      = "ssh port"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = [var.sg_cidr_blocks]
    ipv6_cidr_blocks = [var.sg_ipv6_cidr_blocks]
  }

  ingress {
    description      = "backend port"
    from_port        = 9001
    to_port          = 9001
    protocol         = "tcp"
    cidr_blocks      = [var.sg_cidr_blocks]
    ipv6_cidr_blocks = [var.sg_ipv6_cidr_blocks]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name   = var.jenkins_sg_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

//Creating an EC2 instance to run Jenkins and Docker
resource "aws_instance" "JenkinsEC2" {
  ami                    = var.jenkinsEc2_ami_id
  instance_type          = var.jenkinsEc2_instance_type
  key_name               = var.akp_key_name
  subnet_id              = aws_subnet.publicSubnetA.id
  vpc_security_group_ids = [aws_security_group.JenkinsEC2_sg.id]

  tags = {
    Name   = var.jenkinsEc2_name
    Cohort = 3
  }

  associate_public_ip_address = true

  depends_on = [
    aws_subnet.publicSubnetA,
    aws_key_pair.terraformKey,
    aws_security_group.JenkinsEC2_sg
  ]
}

//Creating an EC2 instance to run Jenkins and Docker
resource "aws_instance" "TestEC2" {
  ami                    = var.jenkinsEc2_ami_id
  instance_type          = var.jenkinsEc2_instance_type
  key_name               = var.akp_key_name
  subnet_id              = aws_subnet.publicSubnetA.id
  vpc_security_group_ids = [aws_security_group.JenkinsEC2_sg.id]

  tags = {
    Name   = var.testEc2_name
    Cohort = 3
  }

  associate_public_ip_address = true

  depends_on = [
    aws_subnet.publicSubnetA,
    aws_key_pair.terraformKey,
    aws_security_group.JenkinsEC2_sg
  ]
}

//Creating a Security Group for RDS
resource "aws_security_group" "rds_sg" {
  name        = var.rds_sg_name
  description = var.rds_sg_description
  vpc_id      = aws_vpc.main_vpc.id

  ingress {
    description      = "database port"
    from_port        = 3306
    to_port          = 3306
    protocol         = "tcp"
    cidr_blocks      = [var.sg_cidr_blocks]
    ipv6_cidr_blocks = [var.sg_ipv6_cidr_blocks]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name   = var.rds_sg_name
    Cohort = 3
  }

  depends_on = [
    aws_vpc.main_vpc
  ]
}

//Creating a DB Subnet Group
resource "aws_db_subnet_group" "prod_rds_subnet_group" {
  name       = var.subGroup_name
  subnet_ids = [aws_subnet.publicSubnetA.id, aws_subnet.publicSubnetB.id, aws_subnet.publicSubnetC.id]

  tags = {
    Name   = var.subGroup_name
    Cohort = 3
  }

  depends_on = [
    aws_subnet.publicSubnetA,
    aws_subnet.publicSubnetB,
    aws_subnet.publicSubnetC
  ]
}

//Creating an RDS to host our mysql database
resource "aws_db_instance" "prod_rds" {
  allocated_storage      = 20
  engine                 = var.rds_engine
  engine_version         = var.rds_engine_version
  instance_class         = var.rds_instance_class
  name                   = var.rds_db_name
  identifier             = var.rds_identifier
  username               = var.rds_username
  password               = var.rds_password
  vpc_security_group_ids = [aws_security_group.rds_sg.id]
  db_subnet_group_name   = aws_db_subnet_group.prod_rds_subnet_group.id
  publicly_accessible    = true
  parameter_group_name   = var.rds_pgn
  skip_final_snapshot    = true

  depends_on = [
    aws_security_group.rds_sg,
    aws_db_subnet_group.prod_rds_subnet_group
  ]
}


