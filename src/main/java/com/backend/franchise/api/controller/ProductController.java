package com.backend.franchise.api.controller;

import com.backend.franchise.api.dto.request.CreateProductRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.request.UpdateStockRequest;
import com.backend.franchise.api.dto.response.ProductResponse;
import com.backend.franchise.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Productos")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/branches/{branchId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Agregar producto a una sucursal")
    public Mono<ProductResponse> addProduct(
            @PathVariable String branchId,
            @Valid @RequestBody CreateProductRequest request) {
        return productService.addProduct(branchId, request);
    }

    @DeleteMapping("/api/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar producto")
    public Mono<Void> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/api/products/{productId}/stock")
    @Operation(summary = "Modificar stock de producto")
    public Mono<ProductResponse> updateStock(
            @PathVariable String productId,
            @Valid @RequestBody UpdateStockRequest request) {
        return productService.updateStock(productId, request);
    }

    @PatchMapping("/api/products/{productId}/name")
    @Operation(summary = "Actualizar nombre de producto")
    public Mono<ProductResponse> updateName(
            @PathVariable String productId,
            @Valid @RequestBody UpdateNameRequest request) {
        return productService.updateName(productId, request);
    }
}