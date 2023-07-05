package com.atacadista.orderProduct;

import com.atacadista.establishment.EstablishmentRequestDTO;
import com.atacadista.order.Order;
import com.atacadista.order.OrderRequestDTO;
import com.atacadista.product.ProductRequestDTO;

public record OrderProductRequestDTO(
        OrderRequestDTO order,
        ProductRequestDTO product,
        Integer quantity,
        Float price
) { }
