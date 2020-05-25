package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.Users;
import com.upgrad.quora.service.entity.user_auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AdminBusinessService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Users deleteUser(String userUuid, String authorization) {
        user_auth userAuthTokenEntity = userDao.FindByAuthToken(authorization);
        if(userAuthTokenEntity == null)
        {
            //todo: throw exception
        }

        if(userAuthTokenEntity.getLogoutAt()!=null)
        {
            //   throw AuthenticationFailedException(); todo: Throw exception
        }

        if(Objects.equals("nonadmin", userAuthTokenEntity.getUser().getRole()))
        {
            // todo: throw exception
        }

        Users userEntity =  userDao.deleteUser(userUuid);
        if(userEntity == null){
            // todo: throw exception   throw new ResourceNotFoundException("USR-001", "User not found");
        }
        return userEntity;

    }
}
