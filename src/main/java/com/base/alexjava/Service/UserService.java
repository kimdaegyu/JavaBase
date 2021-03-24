package com.base.alexjava.Service;

import com.base.alexjava.mongo.model.MongoUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserService {
    @Autowired
    MongoTemplate mongoTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public void loadUserData() throws JsonProcessingException {
        List<MongoUser> updateUserList = new ArrayList<MongoUser>();
        List<MongoUser> userList = mongoTemplate.findAll(MongoUser.class);
        for (MongoUser userData: userList ) {
            log.info(objectMapper.writeValueAsString(userData));
            userData.setLoadCount(userData.getLoadCount()+1);
            updateUserList.add(userData);
        }
        //mongoTemplate.upsert(userList);
        log.info("UserService:loadUserData_finished");
    }

    public int addUserTest() {
        Random rand = new Random();
        int userId = rand.nextInt();
        MongoUser mongoUser = new MongoUser();
        mongoUser.setId(userId);
        mongoUser.setUsername("alex"+userId);
        mongoUser.setEmail("dakunara@gmail.com");

        mongoTemplate.insert(mongoUser);
        log.info("UserService:addUserTest");
        return userId;
    }
}
