package com.ti.sistemareservaturnos.controller;


import com.ti.sistemareservaturnos.model.Turno;
import com.ti.sistemareservaturnos.service.impl.OdontologoService;
import com.ti.sistemareservaturnos.service.impl.PacienteService;
import com.ti.sistemareservaturnos.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Turno> saveTurno(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

        if (pacienteService.findById(turno.getPaciente().getId()).isPresent() && odontologoService.findById(turno.getOdontologo().getId()).isPresent())
            response = ResponseEntity.ok(turnoService.save(turno));
        else
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return response;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> findByIdTurno(@PathVariable Long id){
        Turno turno = turnoService.findById(id).orElse(null);
        return  ResponseEntity.ok(turno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.update(turno));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurno(@PathVariable Long id) {
        ResponseEntity<String> response;
        if (turnoService.findById(id).isPresent()) {
            turnoService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> findAllTurnos() {
        return ResponseEntity.ok(turnoService.findAll());
    }
}
