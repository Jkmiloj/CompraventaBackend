package com.BuyAndSell.Compraventa.persistence.repositoryImplPerson;
import com.BuyAndSell.Compraventa.domain.PersonDto;
import com.BuyAndSell.Compraventa.persistence.entitiesPerson.PersonaEntity;
import com.BuyAndSell.Compraventa.persistence.repositoryPerson.CrudRepository;
import com.BuyAndSell.Compraventa.persistence.repositoryPerson.PersonRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    CrudRepository crudRepository;

    public PersonRepositoryImpl(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public List<PersonDto> getAll(){
        List<PersonDto> personList = new ArrayList<>();
        List<PersonaEntity> personaEntityList = crudRepository.findAll();
        personaEntityList.forEach(personaEntity -> {
            PersonDto personDto = new PersonDto(
                personaEntity.getCc(),
                personaEntity.getNombre(),
                personaEntity.getApellido1(),
                personaEntity.getApellido2(),
                personaEntity.getEdad(),
                personaEntity.getGenero(),
                personaEntity.getEstado()
            );
            personList.add(personDto);
        });
        return personList;
    }

    @Override
    public List<PersonDto> getByState(String estado){
        List<PersonDto> personList = new ArrayList<>();
        List<PersonaEntity> personaEntityList = crudRepository.getByState(estado);
        personaEntityList.forEach(personaEntity -> {
            PersonDto personDto = new PersonDto(
                    personaEntity.getCc(),
                    personaEntity.getNombre(),
                    personaEntity.getApellido1(),
                    personaEntity.getApellido2(),
                    personaEntity.getEdad(),
                    personaEntity.getGenero(),
                    personaEntity.getEstado()
            );
            personList.add(personDto);
        });
        return personList;
    }

    @Override
    public Optional<PersonaEntity> findById(Integer cc){
        return crudRepository.findById(cc);
    }

    @Override
    public List<PersonDto> getByCc(Integer cc){
        List<PersonDto> personList = new ArrayList<>();
        List<PersonaEntity> personaEntityList = crudRepository.getByCc(cc);
        personaEntityList.forEach(personaEntity -> {
            PersonDto personDto = new PersonDto(
                    personaEntity.getCc(),
                    personaEntity.getNombre(),
                    personaEntity.getApellido1(),
                    personaEntity.getApellido2(),
                    personaEntity.getEdad(),
                    personaEntity.getGenero(),
                    personaEntity.getEstado()
            );
            personList.add(personDto);
        });
        return personList;
    }

    @Override
    public Integer update(PersonDto personDto){
        PersonaEntity personaEntity = new PersonaEntity(
                personDto.getCc(),
                personDto.getNombre(),
                personDto.getApellido1(),
                personDto.getApellido2(),
                personDto.getEdad(),
                personDto.getGenero(),
                personDto.getEstado()
        );
        return crudRepository.save(personaEntity).getCc();
    }

    @Override
    public Integer save(PersonDto personDto){
        PersonaEntity personaEntity = new PersonaEntity(
                personDto.getCc(),
                personDto.getNombre(),
                personDto.getApellido1(),
                personDto.getApellido2(),
                personDto.getEdad(),
                personDto.getGenero(),
                personDto.getEstado()
        );
        return crudRepository.save(personaEntity).getCc();
    }

    @Override
    public void updateByState(Integer cc, String newestado){
        Optional<PersonaEntity> personEntityOptional = crudRepository.findById(cc);

        if (personEntityOptional.isPresent()) {
            PersonaEntity personEntity = personEntityOptional.get();
            personEntity.setEstado(newestado);
            crudRepository.save(personEntity);
        } else {
            throw new IllegalArgumentException("No se puede actualizar, la cédula no existe.");
        }
    }

    @Override
    public Integer save2(PersonaEntity personaEntity) {
        personaEntity.getCc();
        personaEntity.getEstado();
        return crudRepository.save(personaEntity).getCc();
    }
}

