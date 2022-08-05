package com.ua.vinn.MyBetaApp.controllers;

import com.ua.vinn.MyBetaApp.dto.response.MessageResponse;
import com.ua.vinn.MyBetaApp.models.Item;
import com.ua.vinn.MyBetaApp.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<?> getItems(){
        List<Item> all = itemRepository.findAll();
        return ResponseEntity.ok(new MessageResponse(all.toString()));
    }

    @PostMapping("/items")
    public ResponseEntity<?> addItem(@Valid @RequestBody Item item){

        Item byDescription = itemRepository.findByDescription(item.getDescription());

        if (byDescription != null) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("There is already a item with the same description"));
        }
        itemRepository.save(item);
        return ResponseEntity.ok(new MessageResponse("The item was saved successfully!"));
    }

}
