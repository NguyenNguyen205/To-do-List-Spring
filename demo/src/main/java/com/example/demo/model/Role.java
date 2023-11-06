package com.example.demo.model;


import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "role")
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "ID")
    private Long id;
    @Column( name = "Name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

}
