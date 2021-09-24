package com.ti.sistemareservaturnos.controller;


import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Odontologo> saveOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.save(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> findOdontologobyId(@PathVariable Long id) {
        Odontologo odontologo = odontologoService.findById(id).orElse(null);
        return ResponseEntity.ok(odontologo);
    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> findAllOdontologos() {
        return ResponseEntity.ok(odontologoService.findAll());
    }

    @PutMapping()
    public ResponseEntity<Odontologo> updateOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> respuesta = null;
        if(odontologo.getId()!= null && odontologoService.findById(odontologo.getId()).isPresent()) {
            respuesta = ResponseEntity.ok(odontologoService.update(odontologo));
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> respuesta = null;

        if(odontologoService.findById(id).isPresent()) {
            odontologoService.delete(id);
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado correctamente!");
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respuesta;
    }

}
