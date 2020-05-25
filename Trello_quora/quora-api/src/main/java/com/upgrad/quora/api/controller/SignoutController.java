package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.SignoutResponse;
import com.upgrad.quora.service.business.AuthenticationService;
import com.upgrad.quora.service.entity.Users;
import com.upgrad.quora.service.entity.user_auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SignoutController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, path = "user/signout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignoutResponse> login(@RequestHeader("authorization") final String authorization) {
        //Basic dXNlcm5hbWU6cGFzc3dvcmQ=
        //above is a sample encoded text where the username is "username" and password is "password" seperated by a ":"
        String[] decode = authorization.split("Bearer ");

        user_auth userAuthToken = authenticationService.authenticateBearer(decode[1]);
        Users user = userAuthToken.getUser();

        SignoutResponse authorizedUserResponse =  new SignoutResponse().id(user.getUuid()).message("SIGNED OUT SUCCESSFULLY");

        return new ResponseEntity<SignoutResponse>(authorizedUserResponse, HttpStatus.OK);
    }

}
