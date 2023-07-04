package com.atacadista.stock;

import com.atacadista.product.Product;
import com.atacadista.establishment.Establishment;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @EmbeddedId
    StockId id;

    @MapsId("idEstablishment")
    @ManyToOne
    @JoinColumn(name = "idEstablishment")
    public Establishment establishment;

    @MapsId("idProduct")
    @ManyToOne
    @JoinColumn(name = "idProduct")
    public Product product;

    public Integer quantity;

    public Stock(StockRequestDTO data) {
        this.establishment = new Establishment( data.establishment() );
        this.product = new Product( data.product() );
        this.quantity = data.quantity();
    }
}