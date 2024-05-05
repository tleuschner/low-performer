terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  backend "s3" {
    bucket = "tfstate-s3-state-low-performer"
    key    = "my-terraform-project"
    region = "eu-central-1"
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "eu-central-1"
}

data "aws_region" "current" {}

# resource "aws_vpc" "main" {
#   cidr_block = "10.0.0.0/16"
# }

# resource "aws_internet_gateway" "main" {
#   vpc_id = aws_vpc.main.id
# }

resource "aws_apprunner_auto_scaling_configuration_version" "low_performer_apprunner_autoscaling" {
  auto_scaling_configuration_name = "example"

  max_concurrency = 50
  max_size        = 10
  min_size        = 2

  tags = {
    Name = "example-apprunner-autoscaling"
  }
}

resource "aws_apprunner_service" "low_performer_apprunner" {
  service_name                   = "low_performer"
  auto_scaling_configuration_arn = aws_apprunner_auto_scaling_configuration_version.low_performer_apprunner_autoscaling.arn

  instance_configuration {
    cpu    = 512
    memory = 1024
  }

  source_configuration {
    image_repository {
      image_configuration {
        port = "8000"
      }
      image_identifier      = "public.ecr.aws/aws-containers/hello-app-runner:latest"
      image_repository_type = "ECR_PUBLIC"
    }
    auto_deployments_enabled = false
  }

  tags = {
    Name = "example-apprunner-service"
  }
}

# resource "aws_ecs_cluster" "low_performer_cluster" {
#   name = "low-performer-cluster"
# }

# resource "aws_cloudwatch_log_group" "ecs_log_group" {
#   name = "low-performer-logs"
# }

# resource "aws_ecs_cluster_capacity_providers" "low_performer_capacity_provider" {
#   cluster_name       = aws_ecs_cluster.low_performer_cluster.name
#   capacity_providers = ["FARGATE_SPOT", "FARGATE"]

#   default_capacity_provider_strategy {
#     capacity_provider = "FARGATE_SPOT"
#     weight            = 1
#     base              = 1
#   }
# }

# resource "aws_route_table" "example" {
#   vpc_id = aws_vpc.main.id

#   route {
#     cidr_block = "0.0.0.0/0"
#     gateway_id = aws_internet_gateway.main.id
#   }
# }

# resource "aws_route_table_association" "example" {
#   subnet_id      = aws_subnet.low_performer_public_subnet.id
#   route_table_id = aws_route_table.example.id
# }

# resource "aws_security_group" "ecs_service_sg" {
#   name   = "ecs-service-sg"
#   vpc_id = aws_vpc.main.id

#   ingress {
#     protocol         = "tcp"
#     from_port        = 80
#     to_port          = 80
#     cidr_blocks      = ["0.0.0.0/0"]
#     ipv6_cidr_blocks = ["::/0"]
#   }

#   ingress {
#     protocol         = "tcp"
#     from_port        = 443
#     to_port          = 443
#     cidr_blocks      = ["0.0.0.0/0"]
#     ipv6_cidr_blocks = ["::/0"]
#   }

#   egress {
#     protocol         = "-1"
#     from_port        = 0
#     to_port          = 0
#     cidr_blocks      = ["0.0.0.0/0"]
#     ipv6_cidr_blocks = ["::/0"]
#   }
# }

# resource "aws_ecs_service" "low_performer_service" {
#   name            = "low-performer-service"
#   cluster         = aws_ecs_cluster.low_performer_cluster.id
#   task_definition = aws_ecs_task_definition.backend.arn
#   desired_count   = 0
#   launch_type     = "FARGATE"
#   network_configuration {
#     assign_public_ip = true
#     subnets          = [aws_subnet.low_performer_public_subnet.id]
#     security_groups  = [aws_security_group.ecs_service_sg.id]
#   }
#   depends_on = [aws_subnet.low_performer_public_subnet]
# }

# resource "aws_ecs_task_definition" "backend" {
#   family                   = "low-performer-backend"
#   network_mode             = "awsvpc"
#   cpu                      = 1024
#   memory                   = 2048
#   requires_compatibilities = ["FARGATE"]

#   container_definitions = jsonencode([
#     {
#       name      = "first"
#       image     = "public.ecr.aws/ecs-sample-image/amazon-ecs-sample:latest"
#       memory    = 1024
#       cpu       = 1024
#       essential = true
#       portMappings = [
#         {
#           containerPort = 80
#           hostPort      = 80
#         }
#       ]
#     }
#   ])
# }

# resource "aws_subnet" "low_performer_public_subnet" {
#   vpc_id     = aws_vpc.main.id
#   cidr_block = "10.0.0.0/24"
# }

# resource "aws_route_table" "low_performer_rt" {
#   vpc_id = aws_vpc.main.id
#   route {
#     ipv6_cidr_block = "::/0"
#     gateway_id      = aws_internet_gateway.low_performer_internet_gw.id
#   }
# }

# resource "aws_route_table_association" "a" {
#   subnet_id      = aws_subnet.low_performer_public_subnet.id
#   route_table_id = aws_route_table.low_performer_rt.id
# }

# resource "aws_internet_gateway" "low_performer_internet_gw" {
#   vpc_id = aws_vpc.main.id
# }




