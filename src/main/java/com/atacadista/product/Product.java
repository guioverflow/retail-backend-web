package com.atacadista.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idProduct")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    public Integer idProduct;
    public Long GTIN;
    public String name;

    public Product(ProductRequestDTO data) {
        this.GTIN = data.GTIN();
        this.name = data.name();
    }
}
