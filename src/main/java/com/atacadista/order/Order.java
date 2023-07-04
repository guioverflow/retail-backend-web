package com.atacadista.order;

import com.atacadista.establishment.Establishment;
import com.atacadista.supplier.Supplier;
import com.atacadista.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Order {
    public Integer idOrder;
    public Date orderDate;
    public Supplier supplier;
    public Establishment establishment;
    public User user;
}
