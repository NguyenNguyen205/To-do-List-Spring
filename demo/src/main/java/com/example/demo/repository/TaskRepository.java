package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwner_Id(Long id);
    Optional<Task> deleteByOwner_Id(Long id);
}
