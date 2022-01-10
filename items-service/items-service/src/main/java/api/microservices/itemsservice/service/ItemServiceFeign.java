package api.microservices.itemsservice.service;


import api.microservices.itemsservice.clients.ProductFeignClient;
import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductFeignClient clientForeign;

    @Override
    public List<Item> findAll() {
        return clientForeign.listAllProduct().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(clientForeign.getProduct(id), quantity);
    }

    @Override
    public Product save(Product product) {
        return clientForeign.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return clientForeign.update(product, id);
    }

    @Override
    public void delete(Long id) {
        clientForeign.delete(id);
    }
}
