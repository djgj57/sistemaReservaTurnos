package com.ti.sistemareservaturnos.repository.impl;


import com.ti.sistemareservaturnos.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {

}
