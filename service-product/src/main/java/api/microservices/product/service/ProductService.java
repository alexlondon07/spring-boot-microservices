package api.microservices.product.service;

import api.microservices.product.entity.Category;
import api.microservices.product.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listAllProduct();

    public Product getProduct(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product);

    public  Product deleteProduct(Long id);

    public List<Product> findByCategory(Category category);

    public Product updateStock(Long id, Double quantity);
}
