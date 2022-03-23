package api.microservices.itemsservice.service;

import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item findById(Long id, Integer quantity);

    Product save(Product product);

    Product update(Product product, Long id);

    void delete(Long id);
}
