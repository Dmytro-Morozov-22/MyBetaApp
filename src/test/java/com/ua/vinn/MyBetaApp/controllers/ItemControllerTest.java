package com.ua.vinn.MyBetaApp.controllers;

import com.ua.vinn.MyBetaApp.dto.response.MessageResponse;
import com.ua.vinn.MyBetaApp.models.Item;
import com.ua.vinn.MyBetaApp.repositories.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemControllerTest {

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemRepository itemRepository;

    static Item testItem = new Item("Item", "2xkj+3L*f1-T", 0.0);

    @Test
    void getItemsTest() {
        ResponseEntity<?> items = itemController.getItems();
        MessageResponse body = (MessageResponse) items.getBody();
        String message = body.getMessage();
        if(itemRepository.findAll().isEmpty()) {
            assertTrue(message == "[]");
        } else {
            assertTrue(message != "[]");
        }
    }

    @Test
    void addItemTest() {
        testItem.setId("1");
        assertEquals(ResponseEntity.ok(new MessageResponse("The item was saved successfully!")),itemController.addItem(testItem));
    }

    @Test
    void addSameItemTest() {
        testItem.setId("1");
        itemController.addItem(testItem);
        assertEquals(ResponseEntity.badRequest()
                .body(new MessageResponse("There is already a item with the same description")),itemController.addItem(testItem));
    }

    @AfterEach
    public void deleteTestedEntity() {
        itemRepository.deleteById("1");
    }

}