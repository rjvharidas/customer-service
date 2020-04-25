package com.tle.bootcamp.customerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class Users {
    @Id
    public ObjectId id;
    public String username;
    public String password;
}
