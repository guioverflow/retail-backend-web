package com.atacadista.order;

import com.atacadista.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
