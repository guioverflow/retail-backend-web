package com.atacadista.saleProducts;

import com.atacadista.order.OrderRequestDTO;
import com.atacadista.product.ProductRequestDTO;
import com.atacadista.sale.SaleRequestDTO;

public record SaleProductRequestDTO(
        SaleRequestDTO sale,
        ProductRequestDTO product,
        Integer quantity,
        Float price
) { }
