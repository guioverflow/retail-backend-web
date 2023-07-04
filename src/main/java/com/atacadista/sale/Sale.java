package com.atacadista.sale;

import com.atacadista.establishment.Establishment;
import com.atacadista.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Sale {
    public Integer idSale;
    public LocalDateTime saleDate;
    public Establishment establishment;
    public User user;
}
