package com.atacadista.product;

public record ProductResponseDTO(Integer idProduct, Long GTIN, String name) {
    public ProductResponseDTO(Product productBean) {
        this(
                productBean.getIdProduct(),
                productBean.getGTIN(),
                productBean.getName()
        );
    }
}
