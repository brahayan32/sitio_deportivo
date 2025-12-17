package com.proyecto.citiodeportivo.dto;

import lombok.Data;

@Data
public class PagoRequestDTO {
    private Integer idReserva;
    private Integer idCliente;
    private Double monto;
    private String metodo;
    private String estadoPago;
}
