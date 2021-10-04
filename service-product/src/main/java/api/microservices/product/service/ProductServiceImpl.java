package api.microservices.product.service;

import api.microservices.product.exception.ProductDoesNotExistException;
import api.microservices.product.entity.Category;
import api.microservices.product.entity.Product;
import api.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATE");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        existProduct(product.getId());
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productBD = existProduct(id);
        productBD.setStatus("DELETED");
        return productRepository.save(productBD);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productBD = existProduct(id);
        Double stock = productBD.getStock() + quantity;
        productBD.setStock(stock);
        return productRepository.save(productBD);
    }

    private Product existProduct(Long id){
        return productRepository.findById(id).orElseThrow( ()-> new ProductDoesNotExistException(id));
    }
}
