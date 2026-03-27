package com.backend.franchise.api.service;

import com.backend.franchise.api.dto.request.CreateFranchiseRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.response.FranchiseResponse;
import com.backend.franchise.api.dto.response.TopStockProductResponse;
import com.backend.franchise.api.exception.ResourceNotFoundException;
import com.backend.franchise.api.model.Franchise;
import com.backend.franchise.api.repository.BranchRepository;
import com.backend.franchise.api.repository.FranchiseRepository;
import com.backend.franchise.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public Mono<FranchiseResponse> createFranchise(CreateFranchiseRequest request) {
        return franchiseRepository
                .save(Franchise.builder().name(request.getName()).build())
                .map(this::toResponse);
    }

    public Mono<FranchiseResponse> updateName(String id, UpdateNameRequest request) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Franquicia no encontrada: " + id)))
                .flatMap(franchise -> {
                    franchise.setName(request.getName());
                    return franchiseRepository.save(franchise);
                })
                .map(this::toResponse);
    }

    public Flux<TopStockProductResponse> getTopStockProductsByFranchise(String franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Franquicia no encontrada: " + franchiseId)))
                .thenMany(branchRepository.findByFranchiseId(franchiseId))
                .flatMap(branch ->
                        productRepository
                                .findFirstByBranchIdOrderByStockDesc(branch.getId())
                                .map(product -> TopStockProductResponse.builder()
                                        .branchId(branch.getId())
                                        .branchName(branch.getName())
                                        .productId(product.getId())
                                        .productName(product.getName())
                                        .stock(product.getStock())
                                        .build())
                );
    }

    private FranchiseResponse toResponse(Franchise f) {
        return FranchiseResponse.builder().id(f.getId()).name(f.getName()).build();
    }
}