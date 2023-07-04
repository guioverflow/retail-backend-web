package com.atacadista.stock;

import com.atacadista.establishment.EstablishmentRequestDTO;
import com.atacadista.product.ProductRequestDTO;

public record StockRequestDTO(
        EstablishmentRequestDTO establishment,
        ProductRequestDTO product,
        Integer quantity
) { }
