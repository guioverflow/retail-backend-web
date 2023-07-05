package com.atacadista.saleProducts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, SaleProductId> {
}
