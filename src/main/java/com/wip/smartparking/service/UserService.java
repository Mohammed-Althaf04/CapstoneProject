package com.wip.smartparking.service;

import java.util.List;
import com.wip.smartparking.entity.User;
/**
 * Service interface defining the business contract and operations for User management.
 *
 * @author Naveen Muthu
 */

public interface UserService {

    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}