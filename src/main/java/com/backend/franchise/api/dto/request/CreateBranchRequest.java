package com.backend.franchise.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBranchRequest {
    @NotBlank(message = "El nombre de la sucursal es requerido")
    private String name;
}