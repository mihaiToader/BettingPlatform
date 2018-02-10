package com.mt.bettingPlatform.service.iService;

import com.mt.bettingPlatform.domain.User;

/**
 * Created by mtoader on 7/17/2017.
 */
public interface UserService {
    User findByName(String name);
}
