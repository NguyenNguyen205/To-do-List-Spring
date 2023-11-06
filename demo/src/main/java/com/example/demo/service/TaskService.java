package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.record.TaskCreateRequest;
import com.example.demo.record.TaskRequest;
import com.example.demo.record.TaskResponse;
import com.example.demo.record.TaskUpdateRequest;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToTaskResponse).collect(Collectors.toList());
    }

    public List<TaskResponse> findAllTasksByUser(Long id) {
        List<Task> tasks = taskRepository.findByOwner_Id(id);
        return tasks.stream().map(this::mapToTaskResponse).collect(Collectors.toList());
    }

    public void createTask(TaskCreateRequest taskCreateRequest) {
        User user = userRepository.findById(taskCreateRequest.uid()).orElseThrow(() -> new EntityNotFoundException("Can't find user with this iD"));
        Task task = Task.builder()
                .name(taskCreateRequest.name())
                .detail(taskCreateRequest.detail())
                .dueDate(taskCreateRequest.dueDate())
                .owner(user)
                .build();
        taskRepository.save(task);
        return;
    }


    public void updateTask(TaskUpdateRequest taskRequest) {
        User owner = userRepository.findById(taskRequest.uid()).orElseThrow(() -> new EntityNotFoundException("Can't find user with that id"));
        Task task = Task.builder()
                .id(taskRequest.id())
                .name(taskRequest.name())
                .detail(taskRequest.description())
                .dueDate(taskRequest.dueDate())
                .owner(owner)
                .build();
        taskRepository.save(task);
        return;
    }

    public void deleteTask(Long id) {
        Task exist = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't found task with that id"));
        taskRepository.deleteById(id);
    }


    private TaskResponse mapToTaskResponse(Task task) {
//        return new TaskResponse(task.getName(), task.getDetail(), task.getCreatedDate(), task.getUpdateDate(), task.getDueDate(), task.getOwner().getId(), task.getOwner().getName());
        return TaskResponse.builder()
                .name(task.getName())
                .detail(task.getDetail())
                .createdDate(task.getCreatedDate())
                .updateDate(task.getUpdateDate())
                .dueDate(task.getDueDate())
                .uid(task.getOwner().getId())
                .username(task.getOwner().getName())
                .build();
    }

}
