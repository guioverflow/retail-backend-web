package com.atacadista.supplier;

public record SupplierResponseDTO(Integer idSupplier, String tradingName, Long phone, String email) {
    public SupplierResponseDTO(Supplier supplier) {
        this(
                supplier.getIdSupplier(),
                supplier.getTradingName(),
                supplier.getPhone(),
                supplier.getEmail()
        );
    }
}
