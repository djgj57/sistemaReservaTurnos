package com.ti.sistemareservaturnos.service.impl;


import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.repository.impl.IOdontologoRepository;
import com.ti.sistemareservaturnos.service.contracts.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IEntityService<Odontologo> {

    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo save(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> findById(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

//    @Override
//    public Odontologo update(Odontologo odontologoNew) {
//        Odontologo odonTemp = odontologoRepository.findById(odontologoNew.getId()).get();
//        odonTemp.setNombre(odontologoNew.getNombre());
//        odonTemp.setApellido(odontologoNew.getApellido());
//        odonTemp.setMatricula(odontologoNew.getMatricula());
//        odontologoRepository.save(odonTemp);
//        return odonTemp;
//    }

    @Override
    public Odontologo update(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void delete(Long id) {
        if (odontologoRepository.findById(id).isPresent()) {
            odontologoRepository.deleteById(id);
            System.out.println("Eliminado con exito!");
        } else {
            System.out.println("Odontologo no encontrado!");
        }
    }

    public Odontologo findOdontologoByMatricula(Integer matricula) {
        return odontologoRepository.findOdontologoByMatricula(matricula);
    }
}
