package com.backend.franchise.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franchises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Franchise {
    @Id
    private String id;
    private String name;
}