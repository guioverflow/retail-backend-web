package com.atacadista.user;

import com.atacadista.establishment.EstablishmentResponseDTO;

public record UserResponseDTO(
        Integer idUser,
        EstablishmentResponseDTO establishment,
        String displayName,
        UserRole userRole,
        String username,
        String password
    ) {

    public UserResponseDTO(User user) {
        this(
                user.getIdUser(),
                new EstablishmentResponseDTO( user.getEstablishment() ),
                user.getDisplayName(),
                user.getUserRole(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
