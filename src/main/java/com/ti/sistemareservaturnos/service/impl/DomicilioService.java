package com.ti.sistemareservaturnos.service.impl;


import com.ti.sistemareservaturnos.model.Domicilio;
import com.ti.sistemareservaturnos.repository.impl.IDomicilioRepository;
import com.ti.sistemareservaturnos.service.contracts.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IEntityService<Domicilio> {

    private IDomicilioRepository domicilioRepository;

    @Autowired
    public DomicilioService(IDomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public Domicilio save(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public Optional<Domicilio> findById(Long id) {
        return domicilioRepository.findById(id);
    }

    @Override
    public List<Domicilio> findAll() {
        return domicilioRepository.findAll();
    }

//    @Override
//    public Domicilio update(Domicilio domicilioNew) {
//        Domicilio domTemp = domicilioRepository.findById(domicilioNew.getId()).get();
//        domTemp.setCalle(domicilioNew.getCalle());
//        domTemp.setNumero(domicilioNew.getNumero());
//        domTemp.setLocalidad(domicilioNew.getLocalidad());
//        domTemp.setProvincia(domicilioNew.getProvincia());
//        domicilioRepository.save(domTemp);
//        return domTemp;
//    }

    @Override
    public Domicilio update(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public void delete(Long id) {
        if (domicilioRepository.findById(id).isPresent()) {
            domicilioRepository.deleteById(id);
            System.out.println("Eliminado con exito!");
        } else {
            System.out.println("Domicilio no encontrado!");
        }
    }
}