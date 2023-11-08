package com.BuyAndSell.Compraventa.persistence.repositoryImplVehicle;
import com.BuyAndSell.Compraventa.domain.VehicleDto;
import com.BuyAndSell.Compraventa.persistence.entitiesVehicle.VehiculoEntity;
import com.BuyAndSell.Compraventa.persistence.repositoryVehicle.CrudVRepository;
import com.BuyAndSell.Compraventa.persistence.repositoryVehicle.VehicleRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    CrudVRepository crudVRepository;

    public VehicleRepositoryImpl(CrudVRepository crudVRepository) {
        this.crudVRepository = crudVRepository;
    }

    @Override
    public List<VehicleDto> getAll(){
        List<VehicleDto> vehicleList = new ArrayList<>();
        List<VehiculoEntity> vehiculoEntityList = crudVRepository.findAll();
        vehiculoEntityList.forEach(vehiculoEntity -> {
            VehicleDto vehicles = new VehicleDto(
                 vehiculoEntity.getPlaca(),
                 vehiculoEntity.getTipo(),
                 vehiculoEntity.getCilindraje(),
                 vehiculoEntity.getModelo(),
                 vehiculoEntity.getMarca(),
                 vehiculoEntity.getCiudad(),
                 vehiculoEntity.getEstado()
            );
            vehicleList.add(vehicles);
        });
        return vehicleList;
    }

    @Override
    public List<VehicleDto> getByStateV(String estado){
        List<VehicleDto> vehicleList = new ArrayList<>();
        List<VehiculoEntity> vehiculoEntityList = crudVRepository.getByStateV(estado);
        vehiculoEntityList.forEach(vehiculoEntity -> {
            VehicleDto vehicles = new VehicleDto(
                    vehiculoEntity.getPlaca(),
                    vehiculoEntity.getTipo(),
                    vehiculoEntity.getCilindraje(),
                    vehiculoEntity.getModelo(),
                    vehiculoEntity.getMarca(),
                    vehiculoEntity.getCiudad(),
                    vehiculoEntity.getEstado()
            );
            vehicleList.add(vehicles);
        });
        return vehicleList;
    }

    @Override
    public Optional<VehiculoEntity> findById(String placa){
        return crudVRepository.findById(placa);
    }

    @Override
    public List<VehicleDto> getByPlaca(String placa){
        List<VehicleDto> vehicleList = new ArrayList<>();
        List<VehiculoEntity> vehiculoEntityList = crudVRepository.getByPlaca(placa);
        vehiculoEntityList.forEach(vehiculoEntity -> {
            VehicleDto vehicles = new VehicleDto(
                    vehiculoEntity.getPlaca(),
                    vehiculoEntity.getTipo(),
                    vehiculoEntity.getCilindraje(),
                    vehiculoEntity.getModelo(),
                    vehiculoEntity.getMarca(),
                    vehiculoEntity.getCiudad(),
                    vehiculoEntity.getEstado()
            );
            vehicleList.add(vehicles);
        });
        return vehicleList;
    }

    @Override
    public String update(VehicleDto vehicles, String placa){
        VehiculoEntity vehiculoEntity = new VehiculoEntity(
            vehicles.getPlaca(),
            vehicles.getTipo(),
            vehicles.getCilindraje(),
            vehicles.getModelo(),
            vehicles.getMarca(),
            vehicles.getCiudad(),
            vehicles.getEstado()
        );
        return crudVRepository.save(vehiculoEntity).getPlaca();
    }

    @Override
    public String save(VehicleDto vehicles){
        VehiculoEntity vehiculoEntity = new VehiculoEntity(
                vehicles.getPlaca().toUpperCase(),
                vehicles.getTipo(),
                vehicles.getCilindraje(),
                vehicles.getModelo(),
                vehicles.getMarca(),
                vehicles.getCiudad(),
                vehicles.getEstado()
        );
        return crudVRepository.save(vehiculoEntity).getPlaca();
    }

    @Override
    public void updateByStateV(String placa, String newestado){
        Optional<VehiculoEntity> personEntityOptional = crudVRepository.findById(placa);

        if (personEntityOptional.isPresent()) {
            VehiculoEntity vehiculoEntity = personEntityOptional.get();
            vehiculoEntity.setEstado(newestado);
            crudVRepository.save(vehiculoEntity);
        } else {
            throw new IllegalArgumentException("No se puede actualizar, la placa no existe.");
        }
    }

    @Override
    public String save2(VehiculoEntity personaEntity) {
        personaEntity.getPlaca();
        personaEntity.getEstado();
        return crudVRepository.save(personaEntity).getPlaca();
    }
}
