package com.ua.vinn.MyBetaApp.controllers;

import com.ua.vinn.MyBetaApp.dto.response.MessageResponse;
import com.ua.vinn.MyBetaApp.models.User;
import com.ua.vinn.MyBetaApp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    static User testUser = new User("2xkj+3L*f1-T", "1110");
    static User testUser1 = new User("2xkj+3L*f1-T", "0001");

    @Test
    void registerUserTest() {
        testUser.setId("1");
        assertEquals(ResponseEntity.ok(new MessageResponse("User registered successfully!")), userController.registerUser(testUser));
    }

    @Test
    void registerSameUserTest() {
        testUser.setId("2");
        testUser1.setId("1");
        userRepository.save(testUser1);
        assertEquals(ResponseEntity.badRequest().body(new MessageResponse("There is already a user with the login of " + testUser.getLogin())), userController.registerUser(testUser));
    }

    @Test
    void loginUserTest(){
        testUser.setId("2");
        userController.registerUser(testUser);
        HttpStatus statusCode = userController.loginUser(new User("2xkj+3L*f1-T", "1110")).getStatusCode();
        assertEquals(ResponseEntity.ok().body(testUser).getStatusCode(), statusCode);
    }

    @Test
    void loginWrongUserTest(){
        testUser.setId("2");
        userController.registerUser(testUser);
        HttpStatus statusCode = userController.loginUser(new User("2xkj+3L*f1-H", "1110")).getStatusCode();
        assertEquals(ResponseEntity.badRequest().body(testUser).getStatusCode(), statusCode);
    }

    @AfterEach
    public void deleteTestedEntity() {
        userRepository.deleteById("1");
        userRepository.deleteById("2");
    }

}