package com.ua.vinn.MyBetaApp.repositories;

import com.ua.vinn.MyBetaApp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserBylogin(String login);
}
