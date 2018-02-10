package com.mt.bettingPlatform.service;

import com.mt.bettingPlatform.repository.UserRepository;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.service.iService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mtoader on 7/17/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
