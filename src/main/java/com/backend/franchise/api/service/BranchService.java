package com.backend.franchise.api.service;

import com.backend.franchise.api.dto.request.CreateBranchRequest;
import com.backend.franchise.api.dto.request.UpdateNameRequest;
import com.backend.franchise.api.dto.response.BranchResponse;
import com.backend.franchise.api.exception.ResourceNotFoundException;
import com.backend.franchise.api.model.Branch;
import com.backend.franchise.api.repository.BranchRepository;
import com.backend.franchise.api.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public Mono<BranchResponse> addBranch(String franchiseId, CreateBranchRequest request) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Franquicia no encontrada: " + franchiseId)))
                .flatMap(franchise -> branchRepository.save(
                        Branch.builder()
                                .franchiseId(franchiseId)
                                .name(request.getName())
                                .build()))
                .map(this::toResponse);
    }

    public Mono<BranchResponse> updateName(String id, UpdateNameRequest request) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Sucursal no encontrada: " + id)))
                .flatMap(branch -> {
                    branch.setName(request.getName());
                    return branchRepository.save(branch);
                })
                .map(this::toResponse);
    }

    private BranchResponse toResponse(Branch b) {
        return BranchResponse.builder()
                .id(b.getId())
                .franchiseId(b.getFranchiseId())
                .name(b.getName())
                .build();
    }
}