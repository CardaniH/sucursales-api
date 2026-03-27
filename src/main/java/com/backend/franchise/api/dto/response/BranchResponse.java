package com.backend.franchise.api.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BranchResponse {
    private String id;
    private String franchiseId;
    private String name;
}