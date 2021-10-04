package api.microservices.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException(long id){
        super("Product " + id + " does not exist.");
    }
}