package com.merci.fitapp.repositories;

import com.merci.fitapp.entities.User;
import com.merci.fitapp.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    Optional<Workout> findByTitle(String title);
    Optional<Workout> findByTitleAndUser(String title, User user);

    List<Workout> findByUser(User user);
}
