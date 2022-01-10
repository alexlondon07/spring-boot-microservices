package api.microservices.itemsservice.clients;


import api.microservices.itemsservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductFeignClient {

    @GetMapping("/products")
    List<Product> listAllProduct();

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id);

    @PostMapping("/products")
    Product save(@RequestBody Product product);

    @PutMapping("/products/{id}")
    Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/products/{id}")
    Product delete(@PathVariable Long id);
}
