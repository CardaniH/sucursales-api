package com.backend.franchise.api.service;

import com.backend.franchise.api.dto.request.CreateProductRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.request.UpdateStockRequest;
import com.backend.franchise.api.dto.response.ProductResponse;
import com.backend.franchise.api.exception.ResourceNotFoundException;
import com.backend.franchise.api.model.Product;
import com.backend.franchise.api.repository.BranchRepository;
import com.backend.franchise.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public Mono<ProductResponse> addProduct(String branchId, CreateProductRequest request) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Sucursal no encontrada: " + branchId)))
                .flatMap(branch -> productRepository.save(
                        Product.builder()
                                .branchId(branchId)
                                .name(request.getName())
                                .stock(request.getStock())
                                .build()))
                .map(this::toResponse);
    }

    public Mono<Void> deleteProduct(String productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Producto no encontrado: " + productId)))
                .flatMap(productRepository::delete);
    }

    public Mono<ProductResponse> updateStock(String productId, UpdateStockRequest request) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Producto no encontrado: " + productId)))
                .flatMap(product -> {
                    product.setStock(request.getStock());
                    return productRepository.save(product);
                })
                .map(this::toResponse);
    }

    public Mono<ProductResponse> updateName(String productId, UpdateNameRequest request) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Producto no encontrado: " + productId)))
                .flatMap(product -> {
                    product.setName(request.getName());
                    return productRepository.save(product);
                })
                .map(this::toResponse);
    }

    private ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .branchId(p.getBranchId())
                .name(p.getName())
                .stock(p.getStock())
                .build();
    }
}