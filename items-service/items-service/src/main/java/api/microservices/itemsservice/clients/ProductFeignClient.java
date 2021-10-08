package api.microservices.itemsservice.clients;


import api.microservices.itemsservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "service-product", url ="localhost:8001")
public interface ProductFeignClient {

    @GetMapping("/products")
    List<Product> listAllProduct();

    @GetMapping("/products/{id}")
    Product getProduct(Long id);
}
