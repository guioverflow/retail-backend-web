package com.atacadista.user;

import com.atacadista.establishment.EstablishmentRequestDTO;

public record UserRequestDTO(
        EstablishmentRequestDTO establishment,
        String displayName,
        UserRole userRole,
        String username,
        String password
    ) { }
