package com.atacadista.stock;

import com.atacadista.establishment.EstablishmentResponseDTO;
import com.atacadista.product.ProductResponseDTO;

public record StockResponseDTO(EstablishmentResponseDTO establishment, ProductResponseDTO product, Integer quantity) {
    public StockResponseDTO(Stock stock) {
        this(
                new EstablishmentResponseDTO( stock.getEstablishment() ),
                new ProductResponseDTO( stock.getProduct() ),
                stock.getQuantity()
        );
    }
}
