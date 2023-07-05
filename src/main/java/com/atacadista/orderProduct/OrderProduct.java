package com.atacadista.orderProduct;


import com.atacadista.order.Order;
import com.atacadista.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "orderproducts", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @EmbeddedId
    public OrderProductId id;

    @MapsId("idOrder")
    @ManyToOne
    @JoinColumn(name = "idorder")
    public Order order;

    @MapsId("idProduct")
    @ManyToOne
    @JoinColumn(name = "idproduct")
    public Product product;

    public Integer quantity;
    public Float price;


    public OrderProduct(OrderProductRequestDTO data) {
        this.order = new Order( data.order() );
        this.product = new Product( data.product() );
        this.quantity = data.quantity();
        this.price = data.price();
    }
}
