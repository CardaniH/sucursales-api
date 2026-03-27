package com.backend.franchise.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    @Id
    private String id;
    private String franchiseId;
    private String name;
}