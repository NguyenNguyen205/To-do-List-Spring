package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "taskID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Detail")
    private String detail;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column(name = "CreateDate")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @Column(name = "UpdateDate")
    private LocalDateTime updateDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DueDate")
    private LocalDateTime  dueDate;
    @ManyToOne()
    @JoinColumn( name = "owner", referencedColumnName = "uID", nullable = false)
    private User owner;
}
