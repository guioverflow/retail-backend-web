package com.atacadista.orderProduct;

import com.atacadista.establishment.EstablishmentResponseDTO;
import com.atacadista.order.OrderResponseDTO;
import com.atacadista.product.ProductResponseDTO;

public record OrderProductResponseDTO(OrderResponseDTO order, ProductResponseDTO product, Integer quantity, Float price) {
    public OrderProductResponseDTO(OrderProduct orderProduct) {
        this(
                new OrderResponseDTO( orderProduct.getOrder() ),
                new ProductResponseDTO( orderProduct.getProduct() ),
                orderProduct.getQuantity(),
                orderProduct.getPrice()
        );
    }
}
