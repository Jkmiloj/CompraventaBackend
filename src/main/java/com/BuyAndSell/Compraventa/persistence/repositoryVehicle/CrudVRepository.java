package com.BuyAndSell.Compraventa.persistence.repositoryVehicle;
import com.BuyAndSell.Compraventa.persistence.entitiesVehicle.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudVRepository extends JpaRepository<VehiculoEntity,String> {
  List<VehiculoEntity> findAll();

  @Query(value = "SELECT * FROM VEHICULO WHERE estado =:estado", nativeQuery = true)
  List<VehiculoEntity> getByStateV(@Param("estado") String estado);

  @Query(value = "SELECT * FROM VEHICULO WHERE placa =:placa", nativeQuery = true)
  List<VehiculoEntity> getByPlaca(@Param("placa") String placa);
}
