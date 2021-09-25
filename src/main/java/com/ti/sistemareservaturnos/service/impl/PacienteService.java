package com.ti.sistemareservaturnos.service.impl;



import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.repository.impl.IPacienteRepository;
import com.ti.sistemareservaturnos.service.contracts.IEntityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IEntityService<Paciente> {

    final static Logger log = Logger.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        log.info("Guardando un paciente...");
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        log.info("Buscando un paciente...");
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
        log.info("Buscando todos los pacientes...");
        return pacienteRepository.findAll();
    }

//    @Override
//    public Paciente update(Paciente pacienteNew) {
//        Paciente pacTemp = pacienteRepository.findById(pacienteNew.getId()).get();
//        pacTemp.setNombre(pacienteNew.getNombre());
//        pacTemp.setApellido(pacienteNew.getApellido());
//        pacTemp.setDni(pacienteNew.getDni());
//        pacTemp.setFechaIngreso(pacienteNew.getFechaIngreso());
//        pacTemp.setDomicilio(pacienteNew.getDomicilio());
//        pacienteRepository.save(pacTemp);
//        return pacTemp;
//    }

    @Override
    public Paciente update(Paciente paciente) {
        log.info("Actualizando un paciente...");
        return pacienteRepository.save(paciente);
    }

    @Override
    public void delete(Long id) {
        if (pacienteRepository.findById(id).isPresent()) {
            log.info("Eliminando un paciente...");
            pacienteRepository.deleteById(id);
            System.out.println("Eliminado con exito!");
        } else {
            System.out.println("Paciente no encontrado!");
        }
    }

    public Paciente findPacienteByDni(Integer dni) {
        return pacienteRepository.findPacienteByDNI(dni);
    }

}
