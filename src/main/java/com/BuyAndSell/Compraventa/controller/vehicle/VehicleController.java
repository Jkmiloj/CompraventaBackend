package com.BuyAndSell.Compraventa.controller.vehicle;

import com.BuyAndSell.Compraventa.domain.VehicleDto;
import com.BuyAndSell.Compraventa.domain.serviceVehicle.VehicleService;
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
public class VehicleController {
    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/traer-vehiculos")
    public List<VehicleDto> getAll(){
        return vehicleService.getAll();
    }

    @GetMapping(value = "/estadovehiculo/{estado}")
    public List<VehicleDto> getByStateV(@PathVariable("estado") String estado) {
        return vehicleService.getByStateV(estado);
    }
    @GetMapping(value = "/traer-placa/{placa}")
    public List<VehicleDto> getByPlaca(@PathVariable("placa") String placa){
        return vehicleService.getByPlaca(placa.toUpperCase());
    }

    @PutMapping(value = "/actualizar-vehiculo")
    public String update(@RequestBody VehicleDto vehicles){
        return vehicleService.update(vehicles, vehicles.getPlaca());
    }

    @PostMapping(value = "/crear-vehiculo")
    public String save(@RequestBody VehicleDto vehicles){
        return vehicleService.save(vehicles);
    }

    @PutMapping(value = "/actualizar-estadovehiculo/{placa}")
    public ResponseEntity<String> updateByStateV(@PathVariable("placa") String placa, @RequestBody Map<String, String> requestBody){
        String newestado = requestBody.get("estado");

        if (newestado == null || newestado.isEmpty()){
            return ResponseEntity.badRequest().body("El campo estado no puede estar vacio");
        }
        try {
            vehicleService.updateByStateV(placa.toUpperCase(),newestado);
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
