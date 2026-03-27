variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "ami_id" {
  description = "Ubuntu 22.04 LTS AMI ID"
  type        = string
  default     = "ami-0c7217cdde317cfec"
}

variable "key_name" {
  description = "Nombre del Key Pair en AWS"
  type        = string
  default     = "franchise-key"
}

variable "repo_url" {
  description = "URL del repositorio GitHub"
  type        = string
  default     = "https://github.com/CardaniH/sucursales-api.git"
}
