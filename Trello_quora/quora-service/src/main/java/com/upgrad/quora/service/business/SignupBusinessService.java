package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    public Users signup(Users userEntity){
        //todo: Move to common once AdminBusinessLogic is ready.
        String password = userEntity.getPassword();
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);

        return this.userDao.createUsers(userEntity);
    }


}
