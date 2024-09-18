package com.devland.assignment.personaltask.task;

import com.devland.assignment.personaltask.category.model.Category;
import com.devland.assignment.personaltask.task.model.Priority;
import com.devland.assignment.personaltask.task.model.Status;
import com.devland.assignment.personaltask.task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t " +
            "WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
            "AND (:category IS NULL OR t.category = :category) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:status IS NULL OR t.status = :status)")
    Page<Task> findAll(String title, Category category, Priority priority, Status status, Pageable pageable);
}