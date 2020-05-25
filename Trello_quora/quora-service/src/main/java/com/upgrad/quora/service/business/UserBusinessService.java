package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.Users;
import com.upgrad.quora.service.entity.user_auth;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessService {

    @Autowired
    private UserDao userDao;

    public Users getUser(String userUuid, String authorization) throws AuthenticationFailedException, UserNotFoundException {

        user_auth userAuthTokenEntity = userDao.FindByAuthToken(authorization);
        if(userAuthTokenEntity == null)
        {
        //todo: throw exception
        }

        if(userAuthTokenEntity.getLogoutAt()!=null)
        {
         //   throw AuthenticationFailedException(); todo: Throw exception
        }
            Users userEntity =  userDao.getUser(userUuid);
            if(userEntity == null){
             // todo: throw exception   throw new ResourceNotFoundException("USR-001", "User not found");
            }
            return userEntity;
        }
     // todo: Throw exception   throw new UnauthorizedException("ATH-002", "you are not authorized to fetch user details");
    }

