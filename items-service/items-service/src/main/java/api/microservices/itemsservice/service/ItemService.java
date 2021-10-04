package api.microservices.itemsservice.service;

import api.microservices.itemsservice.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id, Integer quantity);
}
