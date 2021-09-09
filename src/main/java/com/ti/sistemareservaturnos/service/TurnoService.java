package com.ti.sistemareservaturnos.service;

import com.ti.sistemareservaturnos.dao.IDao;
import com.ti.sistemareservaturnos.model.Turno;

import java.util.List;
import java.util.Optional;

public class TurnoService {

    private IDao<Turno> turnoRepository;

    public TurnoService(IDao<Turno> turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrarTurno(Turno turno){
        return  turnoRepository.guardar(turno);
    }
    public List<Turno> listar(){
        return turnoRepository.buscarTodos();
    }
    public void eliminar(Integer id){
        turnoRepository.eliminar(id);
    }
    public Turno actualizar(Turno turno){
        return turnoRepository.actualizar(turno);
    }
    public Optional<Turno> buscar(Integer id){
        return turnoRepository.buscar(id);
    }

}
