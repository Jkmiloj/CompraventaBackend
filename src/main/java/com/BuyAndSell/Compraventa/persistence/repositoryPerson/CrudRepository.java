package com.BuyAndSell.Compraventa.persistence.repositoryPerson;
import com.BuyAndSell.Compraventa.persistence.entitiesPerson.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CrudRepository extends JpaRepository<PersonaEntity, Integer> {

    List<PersonaEntity> findAll();

    @Query(value = "SELECT * FROM PERSON WHERE estado =:estado", nativeQuery = true)
    List<PersonaEntity> getByState(@Param("estado") String estado);

    @Query(value = "SELECT * FROM PERSON WHERE cc =:cc", nativeQuery = true)
    List<PersonaEntity> getByCc(@Param("cc") Integer cc);

}
