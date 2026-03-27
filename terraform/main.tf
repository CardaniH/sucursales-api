terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

resource "aws_security_group" "franchise_sg" {
  name        = "franchise-api-sg"
  description = "Security group for Franchise API"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "franchise-api-sg"
  }
}

resource "aws_instance" "franchise_api" {
  ami           = var.ami_id
  instance_type = "t3.micro"

  vpc_security_group_ids = [aws_security_group.franchise_sg.id]
  key_name               = var.key_name

  user_data = <<-EOF
    #!/bin/bash
    apt-get update -y
    apt-get install -y docker.io git curl
    curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" \
      -o /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
    systemctl enable docker
    systemctl start docker
    cd /home/ubuntu
    git clone ${var.repo_url} app
    cd app
    docker-compose up -d
  EOF

  tags = {
    Name = "franchise-api-server"
  }
}