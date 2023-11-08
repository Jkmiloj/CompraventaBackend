package com.BuyAndSell.Compraventa.persistence.entitiesVehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VEHICULO")
public class VehiculoEntity {

    @Id
    private String placa;
    private String tipo;
    private Integer cilindraje;
    private Integer modelo;
    private String marca;
    private String ciudad;
    private String estado;

    public VehiculoEntity(){
    }
    public VehiculoEntity(String placa, String tipo, Integer cilindraje, Integer modelo, String marca, String ciudad, String estado) {
        this.placa = placa;
        this.tipo = tipo;
        this.cilindraje = cilindraje;
        this.modelo = modelo;
        this.marca = marca;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
