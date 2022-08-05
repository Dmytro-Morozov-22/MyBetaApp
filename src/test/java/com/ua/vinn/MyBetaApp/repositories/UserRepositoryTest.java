package com.ua.vinn.MyBetaApp.repositories;

import com.ua.vinn.MyBetaApp.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.Document;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    static User testUser = new User();

    @BeforeEach
    public void createTestedEntity() {
        testUser.setId("1");
        testUser.setLogin("Novak");
        userRepository.save(testUser);
    }

    @Test
    public void testForNull(){
        assertNotNull(userRepository.findById("1").get());
    }

    @Test
    public void testFindUserBylogin(){
        assertEquals("Novak", userRepository.findUserBylogin(testUser.getLogin()).getLogin());
    }

    @AfterEach
    public void deleteTestedEntity() {
        userRepository.deleteById("1");
    }

}
