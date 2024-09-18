package com.devland.assignment.personaltask.task.model.dto;

import com.devland.assignment.personaltask.category.model.Category;
import com.devland.assignment.personaltask.category.model.dto.CategoryTaskRequestDTO;
import com.devland.assignment.personaltask.task.model.Priority;
import com.devland.assignment.personaltask.task.model.Status;
import com.devland.assignment.personaltask.task.model.Task;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private Status status;

    @Valid
    private CategoryTaskRequestDTO category;

    public Task convertToEntity() {
        Category existingCategory = this.category.convertToEntity();

        return Task.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .category(existingCategory)
                .priority(this.priority)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}