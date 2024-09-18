package com.devland.assignment.personaltask.category.model.dto;

import com.devland.assignment.personaltask.category.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTaskRequestDTO {
    @Positive(message = "ID must be positive number or not zero")
    @NotNull(message = "ID is required")
    private Long id;

    public Category convertToEntity() {
        return Category.builder()
                .id(this.id)
                .build();
    }
}