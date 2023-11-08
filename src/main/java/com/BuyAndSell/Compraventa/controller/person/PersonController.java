package com.BuyAndSell.Compraventa.controller.person;
import com.BuyAndSell.Compraventa.domain.PersonDto;
import com.BuyAndSell.Compraventa.domain.servicePerson.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/traer-todo")
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

    @GetMapping(value = "/estado/{estado}")
    public List<PersonDto> getByState(@PathVariable("estado") String estado){
        return personService.getByState(estado);
    }

    @GetMapping(value = "/documento/{cc}")
    public List<PersonDto> getByCc(@PathVariable("cc") Integer cc){
        return personService.getByCc(cc);
    }

    @PutMapping(value = "/actualizar")
    public Integer update (@RequestBody PersonDto personDto){
        return personService.update(personDto);
    }

    @PostMapping(value = "/crear")
    public Integer save(@RequestBody PersonDto personDto){
            return personService.save(personDto);
    }

    @PutMapping(value = "/actualizar-estado/{cc}")
    public ResponseEntity<String> update2(@PathVariable("cc") Integer cc, @RequestBody Map<String, String> requestBody){
        String newestado = requestBody.get("estado");

        if (newestado == null || newestado.isEmpty()){
            return ResponseEntity.badRequest().body("El campo estado no puede estar vacio");
        }
        try {
            personService.updateByState(cc,newestado);
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
