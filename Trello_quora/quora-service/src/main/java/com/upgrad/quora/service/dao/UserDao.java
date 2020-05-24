package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    public Users createUsers(Users userEntity){
        entityManager.persist((userEntity));
        return userEntity;
    }
}
