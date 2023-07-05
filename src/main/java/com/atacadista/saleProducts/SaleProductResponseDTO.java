package com.atacadista.saleProducts;

import com.atacadista.order.OrderResponseDTO;
import com.atacadista.product.ProductResponseDTO;
import com.atacadista.sale.SaleResponseDTO;

public record SaleProductResponseDTO(SaleResponseDTO sale, ProductResponseDTO product, Integer quantity, Float price) {
    public SaleProductResponseDTO(SaleProduct saleProduct) {
        this(
                new SaleResponseDTO( saleProduct.getSale() ),
                new ProductResponseDTO( saleProduct.getProduct() ),
                saleProduct.getQuantity(),
                saleProduct.getPrice()
        );
    }
}
