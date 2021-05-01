//aws_key_pair
output "aws_key_pair_name" {
  value = aws_key_pair.terraformKey.id
}

//aws_vpc_id
output "aws_vpc_id" {
  value = aws_vpc.main_vpc.id
}

//aws_internet_gateway_id
output "aws_internet_gateway_id" {
  value = aws_internet_gateway.main_ig.id
}

//aws_subnetA_id
output "aws_subnetA_id" {
  value = aws_subnet.publicSubnetA.id
}

//aws_subnetB_id
output "aws_subnetB_id" {
  value = aws_subnet.publicSubnetB.id
}
//aws_subnetB_id
output "aws_subnetC_id" {
  value = aws_subnet.publicSubnetC.id
}

//aws_route_table_id
output "aws_route_table_id" {
  value = aws_route_table.main_rt.id
}

//aws_security_group_jenkins_id
output "aws_security_group_jenkins_id" {
  value = aws_security_group.JenkinsEC2_sg.id
}

//aws_instance_jenkins_id
output "aws_instance_jenkins_id" {
  value = aws_instance.JenkinsEC2.id
}

//aws_instance_jenkins_public_ip_address
output "aws_instance_jenkins_public_ip_address" {
  value = aws_instance.JenkinsEC2.public_ip
}

//aws_instance_test_id
output "aws_instance_test_id" {
  value = aws_instance.TestEC2.id
}

//aws_instance_test_public_ip_address
output "aws_instance_test_public_ip_address" {
  value = aws_instance.TestEC2.public_ip
}

//aws_security_group_rds_id
output "aws_security_group_rds_id" {
  value = aws_security_group.rds_sg.id
}

//aws_db_subnet_group_name
output "aws_db_subnet_group_name" {
  value = aws_db_subnet_group.prod_rds_subnet_group.id
}

//aws_db_instance_rds
output "aws_db_instance_rds_id" {
  value = aws_db_instance.prod_rds.id
}

//aws_db_instance_rds
output "aws_db_instance_rds_db_name" {
  value = aws_db_instance.prod_rds.name
}

//aws_db_instance_rds
output "aws_db_instance_rds_endpoint" {
  value = aws_db_instance.prod_rds.endpoint
}


