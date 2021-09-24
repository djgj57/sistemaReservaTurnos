package com.ti.sistemareservaturnos.repository.impl;


import com.ti.sistemareservaturnos.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Odontologo findOdontologoByMatricula(Integer matricula);

}
