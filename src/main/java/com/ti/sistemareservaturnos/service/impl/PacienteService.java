package com.ti.sistemareservaturnos.service.impl;



import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.repository.impl.IPacienteRepository;
import com.ti.sistemareservaturnos.service.contracts.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IEntityService<Paciente> {

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
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
        return pacienteRepository.save(paciente);
    }

    @Override
    public void delete(Long id) {
        if (pacienteRepository.findById(id).isPresent()) {
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
