package com.atacadista.sale;

import com.atacadista.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "sales", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idSale")
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsale")
    public Integer idSale;

    @Column(name = "saledate")
    public Date saleDate;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "iduser")
    public User user;

    public Sale(SaleRequestDTO data) {
        this.saleDate = data.saleDate();
        this.user = data.user();
    }
}
