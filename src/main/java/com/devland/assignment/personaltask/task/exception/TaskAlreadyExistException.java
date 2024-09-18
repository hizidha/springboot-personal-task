package com.devland.assignment.personaltask.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TaskAlreadyExistException extends RuntimeException {
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}