package com.mt.bettingPlatform.repository;

import javax.transaction.Transactional;

import com.mt.bettingPlatform.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    @Query("select count(u) from User u  where u.isAdmin = true")
    Integer getAdminNumbers();
}