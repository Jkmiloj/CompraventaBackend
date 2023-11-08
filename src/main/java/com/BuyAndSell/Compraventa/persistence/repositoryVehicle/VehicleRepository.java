package com.BuyAndSell.Compraventa.persistence.repositoryVehicle;
import com.BuyAndSell.Compraventa.domain.VehicleDto;
import com.BuyAndSell.Compraventa.persistence.entitiesVehicle.VehiculoEntity;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    List<VehicleDto> getAll();

    List<VehicleDto> getByStateV(String estado);

    Optional<VehiculoEntity> findById(String placa);

    List<VehicleDto> getByPlaca(String placa);

    String update(VehicleDto vehicles, String placa);

    String save(VehicleDto vehicles);

    void updateByStateV(String placa, String newestado);

    String save2(VehiculoEntity personaEntity);

}
