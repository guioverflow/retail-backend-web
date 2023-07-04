package com.atacadista.establishment;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "establishments")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEstablishment")
public class Establishment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idEstablishment;
    public Long phone;
    public Integer CEP;

    public Establishment(EstablishmentRequestDTO data) {
        this.phone = data.phone();
        this.CEP = data.CEP();
    }
}
