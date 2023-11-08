package com.BuyAndSell.Compraventa.domain.servicePerson;

import com.BuyAndSell.Compraventa.domain.PersonDto;
import com.BuyAndSell.Compraventa.persistence.entitiesPerson.PersonaEntity;
import com.BuyAndSell.Compraventa.persistence.repositoryImplPerson.PersonRepositoryImpl;
import com.BuyAndSell.Compraventa.persistence.repositoryPerson.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonRepository {

    PersonRepositoryImpl personRepository;

    public PersonService(PersonRepositoryImpl personRepository) {
        this.personRepository = personRepository;
    }

    private final String[] estadoList = {"A", "I"};
    private final static String[] generoList = {"F", "M"};

    @Override
    public List<PersonDto> getAll() {
        return personRepository.getAll();
    }

    @Override
    public List<PersonDto> getByState(String estado) {
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("Se debe de ingresar un estado");
        }

        if (!Arrays.stream(estadoList).anyMatch(state -> state.equals(estado))) {
            throw new RuntimeException("El estado no es válido");
        }
        return personRepository.getByState(estado);
    }

    @Override
    public Optional<PersonaEntity> findById(Integer cc) {
        return Optional.empty();
    }

    @Override
    public List<PersonDto> getByCc(Integer cc) {
        if (cc == null) {
            throw new IllegalArgumentException("Debe ingresar un numero de documento de identidad");
        }
        if (cc < 1) {
            throw new IllegalArgumentException("No se permiten valores negativos");
        }
        String ccString = String.valueOf(cc);
        Optional<PersonaEntity> personExist = personRepository.findById(cc);
        if (ccString.length() >= 7 && ccString.length() <= 10) {
            if (personExist.isEmpty()) {
                throw new IllegalArgumentException("El numero de documento no se encuentra registrado");
            } else {
                return personRepository.getByCc(cc);
            }
        } else {
            throw new IllegalArgumentException("La identificación debe de tener entre 7 y 10 dígitos");
        }
    }

    @Override
    public Integer update(PersonDto personDto) {
        Optional<PersonaEntity> personExist = personRepository.findById(personDto.getCc());
        if (personExist.isEmpty()) {
            throw new IllegalArgumentException("El numero de documento no se encuentra registrado");
        }
        String ccString = String.valueOf(personDto.getCc());
        if (ccString.length() >= 7 && ccString.length() <= 10) {
            return personRepository.update(personDto);
        } else {
            throw new IllegalArgumentException("La identificación debe de tener entre 7 y 10 dígitos");
        }
    }


    @Override
    public Integer save(PersonDto personDto) {
        if (personDto.getCc() == null) {
            throw new IllegalArgumentException("El documento no debe estar vacio");
        }
        if (personDto.getCc() <= 0) {
            throw new IllegalArgumentException("La cédula debe ser mayor de 0");
        }
        if (personDto.getEdad() < 18) {
            throw new IllegalArgumentException("Debes de ser mayor de edad");
        }

        Optional<PersonaEntity> personExist = personRepository.findById(personDto.getCc());
        if (personExist.isPresent()) {
            throw new IllegalArgumentException("El numero de documento se encuentra registrado");
        }

        return personRepository.save(personDto);
    }

    @Override
    public void updateByState(Integer cc, String newestado) {
        if (cc == null || newestado == null || newestado.isEmpty()) {
            throw new IllegalArgumentException("La cédula y el estado son obligatorios");
        }
        Optional<PersonaEntity> personExist = personRepository.findById(cc);
        if (personExist.isEmpty()) {
            throw new IllegalArgumentException("Documento no existe");
        }
        String ccString = String.valueOf(cc);
        if (ccString.length() >= 7 && ccString.length() <= 10) {
            if (personExist.isPresent()) {
                if (!Arrays.asList("A", "I").contains(newestado.toUpperCase())) {
                    throw new IllegalArgumentException("El estado no es valido");
                }
                PersonaEntity personaEntity = personExist.get();
                personaEntity.setEstado(newestado.toUpperCase());
                personRepository.save2(personaEntity);
            }
        }
    }

    @Override
    public Integer save2(PersonaEntity personaEntity) {
        return personRepository.save2(personaEntity);
    }
}

