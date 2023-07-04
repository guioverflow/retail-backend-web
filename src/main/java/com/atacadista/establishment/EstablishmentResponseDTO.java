package com.atacadista.establishment;

public record EstablishmentResponseDTO(Integer idEstablishment, Long phone, Integer CEP) {
    public EstablishmentResponseDTO(Establishment establishmentBean) {
        this(
                establishmentBean.getIdEstablishment(),
                establishmentBean.getPhone(),
                establishmentBean.getCEP()
        );
    }
}
