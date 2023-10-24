package com.merci.fitapp.serviceImpl;

import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.UserRepository;
import com.merci.fitapp.services.UserService;
import com.merci.fitapp.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getLoggedUser() throws ServiceException {
    if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()== "anonymousUser") {
        throw  new ServiceException(("You are not logged in"));
    }

    String email;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if(principal instanceof UserDetails) {
        email = ((UserDetails) principal).getUsername();
    } else {
        email = principal.toString();
    }
    return userRepository.findByEmail(email).orElseThrow(() -> new ServiceException("Could not find user!"));
    }


    public User saveUser(User user) throws ServiceException {
        try {
            return userRepository.save(user);
        }catch (ServiceException e) {
            throw  new ServiceException("Could not save user");
        }
    }


    public String updateHeight(Double height) {
        try{
            // get logged in user
            User user = getLoggedUser();
            user.setHeight(height);
            saveUser(user);

            return "" + height;
        }catch (ServiceException e){
            throw new ServiceException("Could not update height");
        }
    }

    public String updateWeight(Double weight) {
        try{
            User user = getLoggedUser();
            user.setWeight(weight);
            saveUser(user);
            return "" + weight;
        }catch (ServiceException e){
            throw  new ServiceException("Could not update weight");
        }
    }
}
