package com.devland.assignment.personaltask.category.model;

import com.devland.assignment.personaltask.category.model.dto.CategoryResponseDTO;
import com.devland.assignment.personaltask.category.model.dto.CategoryTaskResponseDTO;
import com.devland.assignment.personaltask.task.model.Task;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    public CategoryTaskResponseDTO convertToTaskResponse() {
        return CategoryTaskResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public CategoryResponseDTO convertToResponse() {
        return CategoryResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}