package com.atacadista.sale;

import com.atacadista.user.User;

import java.util.Date;

public record SaleRequestDTO(
        Date saleDate,
        User user
) { }
