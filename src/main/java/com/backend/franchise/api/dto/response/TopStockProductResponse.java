package com.backend.franchise.api.dto.response;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TopStockProductResponse {
    private String branchId;
    private String branchName;
    private String productId;
    private String productName;
    private Integer stock;
}