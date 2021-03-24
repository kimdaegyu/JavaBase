package com.base.alexjava.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="user")
public class MongoUser {
    @Id
    private Integer id;
    private String username;
    private String email;
    private Integer loadCount;
}
