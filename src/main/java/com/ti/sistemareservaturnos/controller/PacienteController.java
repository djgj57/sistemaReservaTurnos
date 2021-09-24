package com.ti.sistemareservaturnos.controller;

import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController  {


    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.save(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable Long id){
        Paciente paciente = pacienteService.findById(id).orElse(null);
        return  ResponseEntity.ok(paciente);
    }

    @PutMapping()
    public ResponseEntity<Paciente> updatePaciente(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;

        if (paciente.getId() != null && pacienteService.findById(paciente.getId()).isPresent())
            response = ResponseEntity.ok(pacienteService.update(paciente));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (pacienteService.findById(id).isPresent()) {
            pacienteService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> findAllPaciente(){
        return ResponseEntity.ok(pacienteService.findAll());
    }


}
