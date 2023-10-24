package com.merci.fitapp.services;

import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;

public interface UserService {
    User getLoggedUser() throws ServiceException;

    String updateHeight(Double newHeight) throws ServiceException;

    String updateWeight(Double newWeight) throws ServiceException;

}
