package com.atacadista.sale;

import com.atacadista.establishment.Establishment;
import com.atacadista.user.User;

import java.util.Date;

public record SaleResponseDTO(
        Integer idSale,
        Date saleDate,
        User user
) {
    public SaleResponseDTO(Sale saleBean) {
        this(
                saleBean.getIdSale(),
                saleBean.getSaleDate(),
                saleBean.getUser()
        );
    }
}
