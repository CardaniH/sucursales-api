package com.backend.franchise.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFranchiseRequest {
    @NotBlank(message = "El nombre de la franquicia es requerido")
    private String name;
}