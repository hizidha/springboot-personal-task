package com.devland.assignment.personaltask.task;

import com.devland.assignment.personaltask.category.CategoryService;
import com.devland.assignment.personaltask.category.model.Category;
import com.devland.assignment.personaltask.task.model.Priority;
import com.devland.assignment.personaltask.task.model.Status;
import com.devland.assignment.personaltask.task.model.Task;
import com.devland.assignment.personaltask.task.model.dto.TaskRequestDTO;
import com.devland.assignment.personaltask.task.model.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Page<TaskResponseDTO>> getAll(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Task> pageTasks = this.taskService.findAll(title, category, priority, status, pageable);
        Page<TaskResponseDTO> taskResponseDTOs = pageTasks.map(Task::convertToResponse);

        return ResponseEntity.ok(taskResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getOne(
            @PathVariable("id") Long id
    ) {
        Task existingTask = this.taskService.getOneBy(id);
        TaskResponseDTO taskResponseDTO = existingTask.convertToResponse();

        return ResponseEntity.ok(taskResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDTO> create(
            @RequestBody @Valid TaskRequestDTO taskRequestDTO
    ) {
        Task newTask = this.taskService.getTask(taskRequestDTO);
        Task savedTask = this.taskService.create(newTask);
        TaskResponseDTO taskResponseDTO = savedTask.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid TaskRequestDTO taskRequestDTO
    ) {
        Task updatedTask = this.taskService.getTask(taskRequestDTO);
        updatedTask.setId(id);

        Task savedTask = this.taskService.update(updatedTask);
        TaskResponseDTO taskResponseDTO = savedTask.convertToResponse();

        return ResponseEntity.ok().body(taskResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}