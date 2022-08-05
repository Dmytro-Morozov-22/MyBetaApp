package com.ua.vinn.MyBetaApp.repositories;

import com.ua.vinn.MyBetaApp.models.Item;
import com.ua.vinn.MyBetaApp.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    static Item testItem = new Item();

    @BeforeEach
    public void createTestedEntity() {
        testItem.setId("2");
        testItem.setDescription("green");
        itemRepository.save(testItem);
    }

    @Test
    public void testFindAll(){
        List<Item> items = itemRepository.findAll();
        assertTrue(items.size() > 0);
    }

    @Test
    public void testFindByDescription(){
        assertEquals("green", itemRepository.findByDescription(testItem.getDescription()).getDescription());
    }

    @AfterEach
    public void deleteTestedEntity() {
        itemRepository.deleteById("2");
    }

}
