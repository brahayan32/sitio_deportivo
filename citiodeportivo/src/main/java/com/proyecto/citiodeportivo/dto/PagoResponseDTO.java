package com.proyecto.citiodeportivo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PagoResponseDTO {
    private Integer idPago;
    private Integer idReserva;
    private Integer idCliente;
    private Double monto;
    private String metodo;
    private String estadoPago;
    private LocalDateTime fechaPago;
    private Integer procesadoPorAdmin;
}
