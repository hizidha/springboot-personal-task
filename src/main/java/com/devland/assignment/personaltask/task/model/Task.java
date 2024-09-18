package com.devland.assignment.personaltask.task.model;

import com.devland.assignment.personaltask.category.model.Category;
import com.devland.assignment.personaltask.category.model.dto.CategoryTaskResponseDTO;
import com.devland.assignment.personaltask.task.model.dto.TaskResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public TaskResponseDTO convertToResponse() {
        CategoryTaskResponseDTO categoryTaskResponseDTO = this.category.convertToTaskResponse();

        return TaskResponseDTO.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .category(categoryTaskResponseDTO)
                .priority(this.priority)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}