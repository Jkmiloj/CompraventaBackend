package com.BuyAndSell.Compraventa.domain.serviceBuy;
import com.BuyAndSell.Compraventa.domain.BuyDto;
import com.BuyAndSell.Compraventa.domain.serviceVehicle.VehicleService;
import com.BuyAndSell.Compraventa.persistence.entitiesBuy.CompraEntity;
import com.BuyAndSell.Compraventa.persistence.entitiesPerson.PersonaEntity;
import com.BuyAndSell.Compraventa.persistence.entitiesVehicle.VehiculoEntity;
import com.BuyAndSell.Compraventa.persistence.repositiryImplBuy.BuyRepositoryImpl;
import com.BuyAndSell.Compraventa.persistence.repositoryBuy.CrudCRepository;
import com.BuyAndSell.Compraventa.persistence.repositoryImplPerson.PersonRepositoryImpl;
import com.BuyAndSell.Compraventa.persistence.repositoryImplVehicle.VehicleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BuyService {

    BuyRepositoryImpl buyRepository;
    VehicleRepositoryImpl vehicleRepository;
    PersonRepositoryImpl personRepository;
    CrudCRepository crudCRepository;
    VehicleService vehicleService;


    @Autowired
    public BuyService(BuyRepositoryImpl buyRepository, VehicleRepositoryImpl vehicleRepository,
                      PersonRepositoryImpl personRepository, CrudCRepository crudCRepository,
                      VehicleService vehicleService) {
        this.buyRepository = buyRepository;
        this.vehicleRepository = vehicleRepository;
        this.personRepository = personRepository;
        this.crudCRepository = crudCRepository;
        this.vehicleService = vehicleService;
    }


    public List<BuyDto> getAll() {
        return buyRepository.getAll();
    }


    public Integer save(BuyDto buyDto) {

        Optional<PersonaEntity> personaExist = personRepository.findById(buyDto.getCc());
        if (personaExist.isEmpty()) {
            throw new IllegalArgumentException("La persona con CC " + buyDto.getCc() + " no existe.");
        }

        Optional<VehiculoEntity> placaExist = vehicleRepository.findById(buyDto.getPlaca());
        if (placaExist.isEmpty()) {
            throw new IllegalArgumentException("El vehículo con placa " + buyDto.getPlaca() + " no existe.");
        }

        Integer newId = calculateNewId();

        // Crear la entidad de compra
        CompraEntity compraEntity = new CompraEntity();
        compraEntity.setId(newId);
        compraEntity.setFechaCompra(LocalDateTime.now());
        compraEntity.setCc(buyDto.getCc());
        compraEntity.setPlaca(buyDto.getPlaca());


        // Guardar la compra en la base de datos
        CompraEntity savedCompra = crudCRepository.save(compraEntity);

        return savedCompra.getId(); /*newId;*/
    }

    private Integer calculateNewId() {
        // Obtener el ID más grande de la base de datos
        Integer maxId = crudCRepository.findMaxId();

        // Si la base de datos está vacía, iniciar el ID en 1
        if (maxId == null) {
            maxId = 0;
        }

        // Incrementar el ID más grande en 1 para el nuevo ID
        return maxId + 1;
    }
}


