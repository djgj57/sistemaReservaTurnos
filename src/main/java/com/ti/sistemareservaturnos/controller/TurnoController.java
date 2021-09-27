package com.ti.sistemareservaturnos.controller;


import com.ti.sistemareservaturnos.model.Domicilio;
import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.model.Turno;
import com.ti.sistemareservaturnos.service.impl.OdontologoService;
import com.ti.sistemareservaturnos.service.impl.PacienteService;
import com.ti.sistemareservaturnos.service.impl.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    final static Logger log = Logger.getLogger(TurnoController.class);

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

        if (pacienteService.findById(turno.getPaciente().getId()).isPresent() && odontologoService.findById(turno.getOdontologo().getId()).isPresent()) {
            response = ResponseEntity.ok(turnoService.save(turno));
        } else {
            log.debug("No es posible encontrar al odontolo/paciente...");
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> findByIdTurno(@PathVariable Long id) {
        Turno turno = turnoService.findById(id).orElse(null);
        return ResponseEntity.ok(turno);
    }

    @PutMapping()
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

    /*Solo para test*/

    @PostMapping("/test")
    public Turno testTurno() {
        Domicilio domicilioTest = new Domicilio(null, "falsa", "123", "lalocalidad", "laprovincia");
        Paciente pacienteTest = new Paciente(null, "mario", "casas", "dni123", LocalDate.of(2021,
                2, 12), domicilioTest, null);
        pacienteService.save(pacienteTest);
        Odontologo odontologoTest = new Odontologo(null, "Mario", "lopez", 453, null);
        odontologoService.save(odontologoTest);
        Turno turnoTest = new Turno(null, LocalDate.of(2021, 3, 3), LocalTime.of(9, 30),
                odontologoTest, pacienteTest);
        return turnoService.save(turnoTest);
    }

}
