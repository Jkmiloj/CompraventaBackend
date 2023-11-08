package com.BuyAndSell.Compraventa.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto {
    private Integer id;
    private LocalDateTime fechaCompra;
    private Integer cc;
    private String placa;

}

