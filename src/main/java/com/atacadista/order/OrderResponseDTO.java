package com.atacadista.order;

import com.atacadista.user.User;

import java.util.Date;

public record OrderResponseDTO(
        Integer idOrder,
        Date orderDate,
        User user,
        Boolean approved,
        User idManager
) {
    public OrderResponseDTO(Order orderBean) {
        this(
                orderBean.getIdOrder(),
                orderBean.getOrderDate(),
                orderBean.getUser(),
                orderBean.getApproved(),
                orderBean.getIdManager()
        );
    }
}
