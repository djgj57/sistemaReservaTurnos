package com.ti.sistemareservaturnos.service.impl;



import com.ti.sistemareservaturnos.model.Turno;
import com.ti.sistemareservaturnos.repository.impl.ITurnoRepository;
import com.ti.sistemareservaturnos.service.contracts.IEntityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IEntityService<Turno> {

    final static Logger log = Logger.getLogger(TurnoService.class);

    private final ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno save(Turno turno) {
        log.info("Guardando un turno...");
        return turnoRepository.save(turno);
    }

    @Override
    public Optional<Turno> findById(Long id) {
        log.info("Buscando un turno...");
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> findAll() {
        log.info("Buscando todos los turnos...");
        return turnoRepository.findAll();
    }

//    @Override
//    public Turno update(Turno turnoNew) {
//        Turno turnoTemp = turnoRepository.findById(turnoNew.getId()).get();
//        turnoTemp.setFechaTurno(turnoNew.getFechaTurno());
//        turnoTemp.setHoraTurno(turnoNew.getHoraTurno());
//        turnoTemp.setOdontologo(turnoNew.getOdontologo());
//        turnoNew.setPaciente(turnoNew.getPaciente());
//        turnoRepository.save(turnoTemp);
//        return turnoTemp;
//    }

    @Override
    public Turno update(Turno turno) {
        log.info("actualizando un turno...");
        return turnoRepository.save(turno);
    }

    @Override
    public void delete(Long id) {
        if (turnoRepository.findById(id).isPresent()) {
            log.info("Eliminando un turno...");
            turnoRepository.deleteById(id);
            System.out.println("Eliminado con exito!");
        } else {
            System.out.println("Turno no encontrado!");
        }
    }


}


