package com.BuyAndSell.Compraventa.persistence.repositiryImplBuy;

import com.BuyAndSell.Compraventa.domain.BuyDto;
import com.BuyAndSell.Compraventa.persistence.entitiesBuy.CompraEntity;
import com.BuyAndSell.Compraventa.persistence.repositoryBuy.BuyRepository;
import com.BuyAndSell.Compraventa.persistence.repositoryBuy.CrudCRepository;
import com.BuyAndSell.Compraventa.persistence.repositoryPerson.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BuyRepositoryImpl implements BuyRepository {

    CrudRepository crudRepository;
    CrudCRepository crudCRepository;

    public BuyRepositoryImpl(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Autowired
    public BuyRepositoryImpl(CrudCRepository crudCRepository) {
        this.crudCRepository = crudCRepository;
    }

    @Override
    public List<BuyDto> getAll() {
        List<BuyDto> compraList = new ArrayList<>();
        List<CompraEntity> compraEntityList = crudCRepository.findAll();
        compraEntityList.forEach(compraEntity -> {
            BuyDto compras = new BuyDto(
                    compraEntity.getId(),
                    compraEntity.getFechaCompra(),
                    compraEntity.getCc(),
                    compraEntity.getPlaca()
            );
            compraList.add(compras);
        });
        return compraList;
    }



    @Override
    public Integer save(BuyDto buyDto) {

        CompraEntity compraEntity = new CompraEntity();
        compraEntity.setCc(buyDto.getCc());
        compraEntity.setPlaca(buyDto.getPlaca());
        compraEntity.setFechaCompra(buyDto.getFechaCompra());

        CompraEntity savedCompra = crudCRepository.save(compraEntity);

        return savedCompra.getId();
    }

}
/* 1. en el servicio validar si la placa existe y la persona existe
 * 2. en la implementacion, traer el numero de id mas grande de la base de datos, sumarle 1
 * 3. cuando la base de datos este vacia, validar*/
