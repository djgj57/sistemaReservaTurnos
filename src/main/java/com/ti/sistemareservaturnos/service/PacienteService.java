package com.ti.sistemareservaturnos.service;


import com.ti.sistemareservaturnos.dao.IDao;
import com.ti.sistemareservaturnos.model.Paciente;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente p) {
        p.setFechaIngreso(new Date());
        return pacienteIDao.guardar(p);
    }

    public Optional<Paciente> buscar(Integer id) {
        return pacienteIDao.buscar(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteIDao.buscarTodos();
    }

    public void eliminar(Integer id) {
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizar(Paciente p) {
        return pacienteIDao.actualizar(p);
    }
}