package com.BuyAndSell.Compraventa.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Integer cc;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer edad;
    private String genero;
    private String estado;

}
