package com.devland.assignment.personaltask.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {
    PENDING("PENDING"),
    PROGRESS("PROGRESS"),
    COMPLETED("COMPLETED");

    private String status;
}