package com.backend.franchise.api.repository;

import com.backend.franchise.api.model.Franchise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends ReactiveMongoRepository<Franchise, String> {
}