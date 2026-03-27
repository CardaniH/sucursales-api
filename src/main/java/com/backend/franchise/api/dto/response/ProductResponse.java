package com.backend.franchise.api.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductResponse {
    private String id;
    private String branchId;
    private String name;
    private Integer stock;
}