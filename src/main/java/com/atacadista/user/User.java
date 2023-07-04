package com.atacadista.user;

import com.atacadista.establishment.Establishment;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idUser;

    @ManyToOne
    @JoinColumn(name = "IdEstablishment")
    public Establishment establishment;
    public String displayName;
    public UserRole userRole;
    public String username;
    public String password;

    public User(UserRequestDTO data) {
        this.establishment = new Establishment( data.establishment() );
        this.displayName = data.displayName();
        this.userRole = data.userRole();
        this.username = data.username();
        this.password = data.password();
    }
}
