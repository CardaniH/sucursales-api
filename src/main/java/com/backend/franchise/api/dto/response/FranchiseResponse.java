package com.backend.franchise.api.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class FranchiseResponse {
    private String id;
    private String name;
}