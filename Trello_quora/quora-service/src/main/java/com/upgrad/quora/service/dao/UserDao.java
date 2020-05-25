package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.Users;
import com.upgrad.quora.service.entity.user_auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    public Users createUsers(Users userEntity){
        entityManager.persist((userEntity));
        return userEntity;
    }

    public Users getUserByEmail(final String email){
        try {
            return entityManager.createNamedQuery("userByEmail", Users.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public user_auth createAuthToken(final user_auth userAuthTokenEntity){
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }
    public void updateUser(final Users updatedUserEntity){
        entityManager.merge(updatedUserEntity);
    }

    public user_auth FindByAuthToken(String token) {
        try {
            return entityManager.createNamedQuery("userByAuthToken", user_auth.class).setParameter("accessToken", token)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public void updateUserEntity(user_auth userEntity) {
        entityManager.merge(userEntity);
    }

    public Users getUser(String Uuid) {
        try {
            return entityManager.createNamedQuery("userByUuid", Users.class).setParameter("uuid", Uuid).getSingleResult();
        }catch (NoResultException nre)
        {
            return null;
        }

    }

    public Users deleteUser(String userUuid) {
        Users user;
        try {
            user = entityManager.createNamedQuery("userByUuid", Users.class).setParameter("uuid", userUuid).getSingleResult();
        }catch (NoResultException nre)
        {
            return null;
        }
        entityManager.remove(user);;
        return user;

    }
}
