package com.devland.assignment.personaltask.task.model.dto;

import com.devland.assignment.personaltask.category.model.dto.CategoryTaskResponseDTO;
import com.devland.assignment.personaltask.task.model.Priority;
import com.devland.assignment.personaltask.task.model.Status;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private CategoryTaskResponseDTO category;
    private Priority priority;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}