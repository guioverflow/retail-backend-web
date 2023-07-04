package com.atacadista.saleProducts;

import com.atacadista.product.Product;
import com.atacadista.sale.Sale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleProduct {
    public Sale idSale;
    public Product idProduct;
    public Integer quantity;
    public Float price;
}
