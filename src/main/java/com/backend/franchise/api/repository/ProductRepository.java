package com.backend.franchise.api.repository;

import com.backend.franchise.api.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByBranchId(String branchId);
    Mono<Product> findFirstByBranchIdOrderByStockDesc(String branchId);
}   