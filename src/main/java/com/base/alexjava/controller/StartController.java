package com.base.alexjava.controller;

import com.base.alexjava.Service.DBService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {
    @Autowired
    DBService dBService;

    @GetMapping("/")
    public String hello() {
        return "Alex World!!";
    }

    @GetMapping("/add_user_test")
    public String addUserTest() throws JsonProcessingException {
        dBService.loadUserData();
        int newUserId = dBService.addUserTest();
        return "Add User Test Complete! : " + newUserId;
    }
}
