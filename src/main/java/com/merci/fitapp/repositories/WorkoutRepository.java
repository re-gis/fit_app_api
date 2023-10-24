package com.merci.fitapp.repositories;

import com.merci.fitapp.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
