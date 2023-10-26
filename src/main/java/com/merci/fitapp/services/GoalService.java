package com.merci.fitapp.services;

import com.merci.fitapp.dtos.CreateGoalDto;
import com.merci.fitapp.entities.FitnessGoal;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.GoalRepository;
import com.merci.fitapp.repositories.UserRepository;
import com.merci.fitapp.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    public String CreateGoal(CreateGoalDto dto){
        if(dto.getDescription() == null || dto.getDescription().isEmpty() ||
                dto.getEndDate() == null || dto.getStartDate() == null) {
            throw new ServiceException("All create goals details are required!");
        }

        User user = userService.getLoggedUser();

        Optional<FitnessGoal> existingGoal = goalRepository.findByUserAndDescriptionAndStartDate(user, dto.getDescription(), dto.getStartDate());
        if (existingGoal.isPresent()) {
            throw new ServiceException("A goal with the given description and start date already exists for the user!");
        }

        if (dto.getGoalStatus() == null || dto.getGoalStatus().trim().isEmpty()) {
            dto.setGoalStatus("ONGOING");
        }

        String status = dto.getGoalStatus().toLowerCase();

        if (!status.equals("ongoing") && !status.equals("achieved") && !status.equals("failed")) {
            throw new ServiceException("Status not allowed!");
        }

        // create a goal
        FitnessGoal goal = new FitnessGoal();
        goal.setGoalStatus("ONGOING");
        goal.setUser(user);
        goal.setEndDate(dto.getEndDate());
        goal.setStartDate(dto.getStartDate());
        goal.setDescription(dto.getDescription());

        if(dto.getGoalStatus() !=null) {
            goal.setGoalStatus(dto.getGoalStatus().toUpperCase());
        }

        goalRepository.save(goal);
        return "Goal has been saved successfully";
    }

    public List<FitnessGoal> getALlYouGoals() {
        // get user
        User user = userService.getLoggedUser();

        List<FitnessGoal> goals = goalRepository.findByUser(user);

        if(goals.size() == 0) {
            throw new ServiceException("No goals found for user " + user.getName());
        }

        return goals;
    }

    public Object getOneOfYourGoals(Integer goal) {
        User user = userService.getLoggedUser();

        // Fetch the specific goal for this user using a repository method
        return goalRepository.findByIdAndUser(goal, user)
                .orElseThrow(() -> new ServiceException("Goal not found or you're not authorized to view it!"));
    }


    public String deleteGoal(Integer id) {
        User user = userService.getLoggedUser();

        // get goal
        FitnessGoal goal = goalRepository.findByIdAndUser(id, user).orElseThrow(() -> new ServiceException("Goal not found or you're not authorised to perform this actions!"));
        // delete ethe goal
        goalRepository.delete(goal);
        return "Goal deleted successfully!";
    }

    public String completeGoal(Integer goalId) {
        // get user and goal
        User user = userService.getLoggedUser();
        FitnessGoal goal = goalRepository.findByIdAndUser(goalId, user).orElseThrow(() -> new ServiceException("Goal not found or you're not authorised to perform this actions!"));
        if(goal == null) {
            throw new ServiceException("Goal not found or you're not authorised to perform this actions!");
        }

        goal.setGoalStatus("ACHIEVED");
        goalRepository.save(goal);
        return "Goal successfully completed!";
    }

    public String uncompleteGoal(Integer goalId) {
        User user = userService.getLoggedUser();
        FitnessGoal goal = goalRepository.findByIdAndUser(goalId, user).orElseThrow(() -> new ServiceException("Goal not found or you're not authorized to perform this action!"));
        if(goal == null) {
            throw new ServiceException("Goal not found or you're not authorised to perform this actions!");
        }
        goal.setGoalStatus("FAILED");
        goalRepository.save(goal);
        return "Goal failed!";
    }
}
