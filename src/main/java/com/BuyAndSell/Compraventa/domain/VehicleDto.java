package com.BuyAndSell.Compraventa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private String placa;
    private String tipo;
    private Integer cilindraje;
    private Integer modelo;
    private String marca;
    private String ciudad;
    private String estado;

}
