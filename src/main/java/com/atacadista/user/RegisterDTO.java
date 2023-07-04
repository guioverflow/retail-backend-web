package com.atacadista.user;

import com.atacadista.establishment.Establishment;

public record RegisterDTO(
        Establishment establishment,
        String displayName,
        UserRole userRole,
        String username,
        String password
) { }
