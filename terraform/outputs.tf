output "public_ip" {
  value       = aws_instance.franchise_api.public_ip
  description = "IP publica del servidor"
}

output "api_url" {
  value       = "http://${aws_instance.franchise_api.public_ip}:8080"
  description = "URL de la API"
}

output "swagger_url" {
  value       = "http://${aws_instance.franchise_api.public_ip}:8080/swagger-ui.html"
  description = "URL de Swagger UI"
}