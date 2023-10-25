package com.merci.fitapp.repositories;

import com.merci.fitapp.entities.FitnessGoal;
import com.merci.fitapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<FitnessGoal, Integer> {
    Optional<FitnessGoal> findByUserAndDescriptionAndStartDate(User user, String description, Date startDate);

    List<FitnessGoal> findByUser(User user);

    Optional<Object> findByIdAndUser(Integer goal, User user);
}
