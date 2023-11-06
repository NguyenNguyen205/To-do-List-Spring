package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Task;
import com.example.demo.record.TaskCreateRequest;
import com.example.demo.record.TaskResponse;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<List<TaskResponse>> getAll() {
        List<TaskResponse> res = taskService.findAllTasks();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskCreateRequest request) {
        taskService.createTask(request);
        return new ResponseEntity<>("Task created successfully", HttpStatus.OK);
    }
}
