package com.backend.franchise.api.controller;

import com.backend.franchise.api.dto.request.CreateBranchRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.response.BranchResponse;
import com.backend.franchise.api.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sucursales")
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/api/franchises/{franchiseId}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Agregar sucursal a una franquicia")
    public Mono<BranchResponse> addBranch(
            @PathVariable String franchiseId,
            @Valid @RequestBody CreateBranchRequest request) {
        return branchService.addBranch(franchiseId, request);
    }

    @PatchMapping("/api/branches/{id}/name")
    @Operation(summary = "Actualizar nombre de sucursal")
    public Mono<BranchResponse> updateName(
            @PathVariable String id,
            @Valid @RequestBody UpdateNameRequest request) {
        return branchService.updateName(id, request);
    }
}