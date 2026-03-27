package com.backend.franchise.api.repository;

import com.backend.franchise.api.model.Branch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BranchRepository extends ReactiveMongoRepository<Branch, String> {
    Flux<Branch> findByFranchiseId(String franchiseId);
}