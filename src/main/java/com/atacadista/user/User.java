package com.atacadista.user;

import com.atacadista.establishment.Establishment;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    public int idUser;

    @ManyToOne
    @JoinColumn(name = "idestablishment")
    public Establishment establishment;

    @Column(name = "displayname")
    public String displayName;

    @Column(name = "userrole")
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
