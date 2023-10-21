package com.merci.fitapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false )
    private String email;

    private String password;

    private int age;

    private Double height;

    private Double weight;

    private String imageUrl;

    @OneToMany(mappedBy = "user")
    private List<FitnessGoal> goals;
}
