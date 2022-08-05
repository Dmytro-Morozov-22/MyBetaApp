package com.ua.vinn.MyBetaApp.repositories;

import com.ua.vinn.MyBetaApp.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    Item findByDescription(String description);
}
