package com.BuyAndSell.Compraventa.domain.serviceVehicle;

import com.BuyAndSell.Compraventa.domain.VehicleDto;
import com.BuyAndSell.Compraventa.persistence.entitiesVehicle.VehiculoEntity;
import com.BuyAndSell.Compraventa.persistence.repositoryImplVehicle.VehicleRepositoryImpl;
import com.BuyAndSell.Compraventa.persistence.repositoryVehicle.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VehicleService implements VehicleRepository {
    VehicleRepositoryImpl vehicleRepository;

    public VehicleService(VehicleRepositoryImpl vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    private final String[] estadoListV = {"A", "I", "V"};
    private final String[] tipoV = {"Moto", "Carro"};

    @Override
    public List<VehicleDto> getAll() {
        return vehicleRepository.getAll();
    }

    @Override
    public List<VehicleDto> getByStateV(String estado) {
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("Se debe de ingresar un estado");
        }
        if (!Arrays.stream(estadoListV).anyMatch(state -> state.equals(estado))) {
            throw new RuntimeException("El estado no es válido");
        }
        return vehicleRepository.getByStateV(estado);
    }

    @Override
    public Optional<VehiculoEntity> findById(String placa) {
        return Optional.empty();
    }

    @Override
    public List<VehicleDto> getByPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar una placa");
        }
        Optional<VehiculoEntity> placaExist = vehicleRepository.findById(placa);

        if (validarPlacaCarro(placa) || validarPlacaMoto(placa)) {
            if (placaExist.isPresent()) {
                return vehicleRepository.getByPlaca(placa);
            } else {
                throw new IllegalArgumentException("La PLaca no existe");
            }
        } else {
            throw new IllegalArgumentException("Formato de placa invalido");
        }
    }


    public static boolean validarPlacaCarro(String placa) {

        String patron = "^[A-Za-z]{3}\\d{3}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(placa.toUpperCase());
        return matcher.matches();
    }

    public static boolean validarPlacaMoto(String placa) {

        String patron = "^[A-Za-z]{3}\\d{2}[A-Za-z]$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(placa.toUpperCase());
        return matcher.matches();
    }

    @Override
    public String update(VehicleDto vehicles, String placa) {
        Optional<VehiculoEntity> placaExist = vehicleRepository.findById(placa);

        if (placaExist.isEmpty()) {
            throw new IllegalArgumentException("La placa no se encuentra registrada");
        }
        return vehicleRepository.update(vehicles, placa);

        /*if (validarPlacaCarro(placa) || validarPlacaMoto(placa)){
            return vehicleRepository.update(vehicles, placa);
        }else {
            throw new IllegalArgumentException("Formato de placa invalido");
        }*/
    }

    @Override
    public String save(VehicleDto vehicles) {
        if (vehicles.getPlaca() == null || vehicles.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar una placa");
        }
        if (!Arrays.stream(tipoV).anyMatch(state -> state.equals(vehicles.getTipo()))) {
            throw new IllegalArgumentException("Tipo de vehiculo no valido, debe ser Moto o Carro");
        }
        Optional<VehiculoEntity> placaExist = vehicleRepository.findById(vehicles.getPlaca().toUpperCase());
        if (vehicles.getCilindraje() < 99) {
            throw new IllegalArgumentException("El cilindraje no es válido");
        }

        if (validarPlacaCarro(vehicles.getPlaca()) || validarPlacaMoto(vehicles.getPlaca())) {
            if (placaExist.isPresent()) {
                throw new IllegalArgumentException("La PLaca se encuentra registrada");
            } else {
                return vehicleRepository.save(vehicles);
            }
        } else {
            throw new IllegalArgumentException("Formato de placa invalido");
        }
    }

    @Override
    public void updateByStateV(String placa, String newestado) {
        if (placa == null || newestado == null || newestado.isEmpty()) {
            throw new IllegalArgumentException("La cédula y el estado son obligatorios");
        }
        Optional<VehiculoEntity> personExist = vehicleRepository.findById(placa);
        if (personExist.isEmpty()) {
            throw new IllegalArgumentException("No se puede actualizar, la placa no existe");
        }
        if (validarPlacaCarro(placa) || validarPlacaMoto(placa)) {
            if (personExist.isPresent()) {
                if (!Arrays.asList("A", "I").contains(newestado.toUpperCase())) {
                    throw new IllegalArgumentException("El estado no es valido");
                }
                VehiculoEntity vehiculoEntity = personExist.get();
                vehiculoEntity.setEstado(newestado.toUpperCase());
                vehicleRepository.save2(vehiculoEntity);
            }
        }
    }

    @Override
    public String save2(VehiculoEntity vehiculoEntity) {
        return vehicleRepository.save2(vehiculoEntity);
    }
}
