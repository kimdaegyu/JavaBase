package com.base.alexjava.Service;

import com.base.alexjava.mongo.model.MongoUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.bulk.BulkWriteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class DBService {
    //https://www.baeldung.com/spring-data-mongodb-tutorial
    // 위 링크에 mongoTemplate 설명 있음

    @Autowired
    MongoTemplate mongoTemplate;

    ObjectMapper objectMapper = new ObjectMapper();
    Random rand = new Random();

    public void loadUserData() throws JsonProcessingException {
        BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, MongoUser.class);
        List<MongoUser> userList = mongoTemplate.findAll(MongoUser.class);
        for (MongoUser userData: userList ) {
            log.info(objectMapper.writeValueAsString(userData));
            int newLoadCount = 1;
            if( userData.getLoadCount() != null ) {
                newLoadCount = userData.getLoadCount() + 1;
            }
            userData.setLoadCount(newLoadCount);
            Query query = new Query().addCriteria(Criteria.where("_id").is(userData.getId()));
            Update update = new Update().set("loadCount", newLoadCount);
            bulkOps.updateOne(query, update);
        }
        BulkWriteResult results = bulkOps.execute();
        log.info("UserService:loadUserData_finished");
    }

    public int addUserTest() {
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
