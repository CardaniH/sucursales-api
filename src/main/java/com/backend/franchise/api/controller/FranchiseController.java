package com.backend.franchise.api.controller;

import com.backend.franchise.api.dto.request.CreateFranchiseRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.response.FranchiseResponse;
import com.backend.franchise.api.dto.response.TopStockProductResponse;
import com.backend.franchise.api.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
@Tag(name = "Franquicias")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear franquicia")
    public Mono<FranchiseResponse> create(@Valid @RequestBody CreateFranchiseRequest request) {
        return franchiseService.createFranchise(request);
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Actualizar nombre de franquicia")
    public Mono<FranchiseResponse> updateName(
            @PathVariable String id,
            @Valid @RequestBody UpdateNameRequest request) {
        return franchiseService.updateName(id, request);
    }

    @GetMapping("/{franchiseId}/top-stock-products")
    @Operation(summary = "Producto con más stock por sucursal")
    public Flux<TopStockProductResponse> getTopStock(@PathVariable String franchiseId) {
        return franchiseService.getTopStockProductsByFranchise(franchiseId);
    }
}