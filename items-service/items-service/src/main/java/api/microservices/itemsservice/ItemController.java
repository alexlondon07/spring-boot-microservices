package api.microservices.itemsservice;

import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;
import api.microservices.itemsservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/list")
    public List<Item> getAll(@RequestParam(name="name", required = false) String name,
                             @RequestHeader(name="token-request", required = false) String token){
        System.out.println(name);
        System.out.println(token);
        return itemService.findAll();
    }

    // @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/view/{id}/quantity/{quantity}")
    public Item getDetail(@PathVariable Long id, @PathVariable Integer quantity){
        return circuitBreakerFactory.create("items").run(
                () -> itemService.findById(id, quantity), e -> alternativeMethod(id, quantity)
        );
    }

    public Item alternativeMethod(Long id, Integer quantity) {
        Product product = Product.builder().id(id).name("Camera Sony").price(500.00).build();
        return Item.builder().quantity(quantity).product(product).build();
    }
}

