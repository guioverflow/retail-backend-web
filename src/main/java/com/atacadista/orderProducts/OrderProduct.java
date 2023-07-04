package com.atacadista.orderProducts;


import com.atacadista.order.Order;
import com.atacadista.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderProduct {
    public Order idOrder;
    public Product idProduct;
    public Integer quantity;
    public Float price;
}
