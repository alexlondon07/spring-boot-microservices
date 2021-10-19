package api.microservices.itemsservice;

import api.microservices.itemsservice.model.Item;
import api.microservices.itemsservice.model.Product;
import api.microservices.itemsservice.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/list")
    public List<Item> getAll(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/view/{id}/quantity/{quantity}")
    public Item getDetail(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    public Item alternativeMethod(Long id, Integer quantity) {
        Product product = Product.builder().id(id).name("Camera Sony").price(500.00).build();
        return Item.builder().quantity(quantity).product(product).build();
    }
}

