package com.ti.sistemareservaturnos.dao.impl;

import com.ti.sistemareservaturnos.dao.IDao;
import com.ti.sistemareservaturnos.model.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurnoListRepository implements IDao<Turno> {
    private List<Turno> turnos;

    public TurnoListRepository() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Optional<Turno> buscar(Integer id) {
        return turnos.stream().filter(turno -> turno.getId().equals(id)).findFirst();
    }

    @Override
    public void eliminar(Integer id) {
        turnos.removeIf(turno -> turno.getId().equals(id));
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return turno;
    }
}
