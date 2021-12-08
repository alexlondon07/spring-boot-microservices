package api.microservices.itemsservice;

import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;
import api.microservices.itemsservice.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Value("${configuration.text}")
    private String text;

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

    @GetMapping("/view/{id}/quantity/{quantity}")
    public Item getDetail(@PathVariable Long id, @PathVariable Integer quantity){
        return circuitBreakerFactory.create("items").run(
                () -> itemService.findById(id, quantity), e -> alternativeMethod(id, quantity, e)
        );
    }

    @CircuitBreaker(name="items", fallbackMethod = "alternativeMethod")
    @GetMapping("/view2/{id}/quantity/{quantity}")
    public Item getDetail2(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    @CircuitBreaker(name="items", fallbackMethod = "alternativeMethod2")
    @TimeLimiter(name="items", fallbackMethod = "alternativeMethod2")
    @GetMapping("/view3/{id}/quantity/{quantity}")
    public CompletableFuture<Item> getDetail3(@PathVariable Long id, @PathVariable Integer quantity){
        return CompletableFuture.supplyAsync( ()-> itemService.findById(id, quantity)) ;
    }

    @GetMapping("/get-config-items")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        logger.info(text);
        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("port", port);

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    public Item alternativeMethod(Long id, Integer quantity, Throwable e) {
        logger.info(e.getMessage());
        Product product = Product.builder().id(id).name("Camera Sony").price(500.00).build();
        return Item.builder().quantity(quantity).product(product).build();
    }

    public CompletableFuture<Item>  alternativeMethod2(Long id, Integer quantity, Throwable e) {
        logger.info(e.getMessage());
        Product product = Product.builder().id(id).name("Camera Sony v2").price(800.00).build();
        return CompletableFuture.supplyAsync( ()->Item.builder().quantity(quantity).product(product).build() ) ;
    }
}

