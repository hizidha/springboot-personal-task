package com.devland.assignment.personaltask.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Priority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
    URGENT("URGENT");

    private String priority;
}