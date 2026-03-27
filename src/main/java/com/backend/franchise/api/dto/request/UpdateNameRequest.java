package com.backend.franchise.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateNameRequest {
    @NotBlank(message = "El nombre es requerido")
    private String name;
}