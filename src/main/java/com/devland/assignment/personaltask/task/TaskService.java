package com.devland.assignment.personaltask.task;

import com.devland.assignment.personaltask.category.CategoryService;
import com.devland.assignment.personaltask.category.model.Category;
import com.devland.assignment.personaltask.task.exception.TaskAlreadyExistException;
import com.devland.assignment.personaltask.task.exception.TaskNotFoundException;
import com.devland.assignment.personaltask.task.model.Priority;
import com.devland.assignment.personaltask.task.model.Status;
import com.devland.assignment.personaltask.task.model.Task;
import com.devland.assignment.personaltask.task.model.dto.TaskRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryService categoryService;

    public Task getTask(TaskRequestDTO taskRequestDTO) {
        taskRequestDTO.setPriority(Priority.valueOf(taskRequestDTO.getPriority().toString().toUpperCase()));
        taskRequestDTO.setStatus(Status.valueOf(taskRequestDTO.getStatus().toString().toUpperCase()));
        return taskRequestDTO.convertToEntity();
    }

    public Page<Task> findAll(String title, String category, String priority, String status, Pageable pageable) {
        Priority priorityEnum = (priority != null && !priority.isEmpty()) ? Priority.valueOf(priority.toUpperCase()) : null;
        Status statusEnum = (status != null && !status.isEmpty()) ? Status.valueOf(status.toUpperCase()) : null;
        Category existingCategory = null;

        if (category != null && !category.isEmpty()) {
            existingCategory = this.categoryService.getOneByName(category);
        }

        return this.taskRepository.findAll(title, existingCategory, priorityEnum, statusEnum, pageable);
    }


    public Task getOneBy(Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
    }

    public Task create(Task newTask) {
        Optional<Task> existingTask = this.taskRepository.findByTitle(newTask.getTitle());

        if (existingTask.isPresent()) {
            throw new TaskAlreadyExistException("Task with title " + newTask.getTitle() + " already exist");
        }

        Category existingCategory = this.categoryService.getOneBy(newTask.getCategory().getId());
        newTask.setCategory(existingCategory);

        return this.taskRepository.save(newTask);
    }

    public Task update(Task updatedTask) {
        Task existingTask = this.getOneBy(updatedTask.getId());

//        Category existingCategory = this.categoryService.getOneBy(updatedTask.getCategory().getId());
//        updatedTask.setCategory(existingCategory);
        updatedTask.setId(existingTask.getId());

        return this.taskRepository.save(updatedTask);
    }

    public void delete(Long id) {
        this.taskRepository.deleteById(this.getOneBy(id).getId());
    }
}