package com.devland.assignment.personaltask.category.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTaskResponseDTO {
    private Long id;
    private String name;
}