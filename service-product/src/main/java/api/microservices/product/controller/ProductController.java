package api.microservices.product.controller;

import api.microservices.product.entity.Category;
import api.microservices.product.entity.Product;
import api.microservices.product.service.ProductService;
import api.microservices.product.validation.EntityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ProductService productService ;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();
        if (null ==  categoryId){
            products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/list")
    public List<Product> getList(){
        return productService.listAllProduct().stream().peek(product -> product.setPort(port)).collect(Collectors.toList());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws InterruptedException {

        if(id.equals(10L)){
            throw new IllegalStateException("Product no found");
        }
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        Product product = productService.getProduct(id);
        product.setPort(port);
        //throw new RuntimeException("Errror no se pudo obetener el producto");
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, BindingResult result) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EntityValidation.formatMessage(result));
        }
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @Valid @RequestBody Product product){
        product.setId(id);
        Product productBD = productService.updateProduct(product);
        return null == productBD ? ResponseEntity.noContent().build() : ResponseEntity.ok(productBD);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id){
        Product productDeleted = productService.deleteProduct(id);
        return null == productDeleted ? ResponseEntity.noContent().build() : ResponseEntity.ok(productDeleted);
    }

    @PutMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(value = "quantity", required = true)
            Double quantity){
        Product product = productService.updateStock(id, quantity);
        return null == product ? ResponseEntity.noContent().build() : ResponseEntity.ok(product);
    }
}
