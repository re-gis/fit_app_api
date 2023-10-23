package com.merci.fitapp.services;

import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;

public interface UserService {
    User getLoggedUser() throws ServiceException;
}
