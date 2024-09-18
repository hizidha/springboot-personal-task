package com.devland.assignment.personaltask.category.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}