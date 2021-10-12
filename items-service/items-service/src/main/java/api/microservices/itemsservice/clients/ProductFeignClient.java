package api.microservices.itemsservice.clients;


import api.microservices.itemsservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductFeignClient {

    @GetMapping("/products")
    List<Product> listAllProduct();

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id);
}
