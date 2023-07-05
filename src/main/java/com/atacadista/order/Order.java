package com.atacadista.order;


import com.atacadista.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idOrder")
public class Order{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    public Integer idOrder;

    @Column(name = "orderdate")
    public Date orderDate;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "iduser")
    public User user;
    public Boolean approved;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "iduser")
    public User idManager;


    public Order(OrderRequestDTO data) {
        this.orderDate = data.orderDate();
        this.user = data.user();
        this.approved = data.approved();
        this.idManager = data.idManager();
    }
}
