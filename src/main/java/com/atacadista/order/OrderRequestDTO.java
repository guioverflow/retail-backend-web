package com.atacadista.order;

import com.atacadista.user.User;

import java.util.Date;

public record OrderRequestDTO(
        Date orderDate,
        User user,
        Boolean approved,
        User idManager
) { }
