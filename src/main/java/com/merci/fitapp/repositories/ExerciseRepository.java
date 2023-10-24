package com.merci.fitapp.repositories;

import com.merci.fitapp.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
