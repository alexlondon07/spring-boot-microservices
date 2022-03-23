package api.microservices.itemsservice.service;

import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
    private static final String URI_SERVICE_PRODUCTS = "http://localhost:8001/products";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(URI_SERVICE_PRODUCTS, Product[].class)));
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        Product product =  restTemplate.getForObject("http://localhost:8001/products/{id}", Product.class, pathVariables);
        return new Item(product, quantity) ;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
