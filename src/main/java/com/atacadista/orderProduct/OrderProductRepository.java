package com.atacadista.orderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
