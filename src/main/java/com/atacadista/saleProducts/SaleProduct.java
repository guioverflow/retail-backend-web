package com.atacadista.saleProducts;


import com.atacadista.product.Product;
import com.atacadista.sale.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "saleproducts", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class SaleProduct {
    @EmbeddedId
    public SaleProductId id;

    @MapsId("idSale")
    @ManyToOne
    @JoinColumn(name = "idsale")
    public Sale sale;

    @MapsId("idProduct")
    @ManyToOne
    @JoinColumn(name = "idproduct")
    public Product product;

    public Integer quantity;
    public Float price;


    public SaleProduct(SaleProductRequestDTO data) {
        this.sale = new Sale( data.sale() );
        this.product = new Product( data.product() );
        this.quantity = data.quantity();
        this.price = data.price();
    }
}
