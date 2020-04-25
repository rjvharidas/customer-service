package com.tle.bootcamp.customerservice.repository;

import com.tle.bootcamp.customerservice.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
}
