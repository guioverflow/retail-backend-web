package com.atacadista.supplier;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "suppliers", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idSupplier")
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsupplier")
    public Integer idSupplier;
    public String tradingName;
    public Long phone;
    public String email;

    public Supplier(SupplierRequestDTO data) {
        this.tradingName = data.tradingName();
        this.phone = data.phone();
        this.email = data.email();
    }
}
