package com.BuyAndSell.Compraventa.persistence.repositoryPerson;
import com.BuyAndSell.Compraventa.domain.PersonDto;
import com.BuyAndSell.Compraventa.persistence.entitiesPerson.PersonaEntity;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<PersonDto> getAll();

    List<PersonDto> getByState(String estado);

    Optional<PersonaEntity> findById(Integer cc);

    List<PersonDto> getByCc(Integer cc);

    Integer update(PersonDto personDto);

    Integer save(PersonDto personDto);

    void updateByState(Integer cc, String newestado);

    Integer save2(PersonaEntity personaEntity);
}
