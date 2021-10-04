package api.microservices.itemsservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private Product product;
    private Integer quantity;

    public Double getTotal(){
        return product.getPrice() * quantity.doubleValue();
    }
}
