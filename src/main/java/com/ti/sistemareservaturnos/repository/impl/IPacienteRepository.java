package com.ti.sistemareservaturnos.repository.impl;


import com.ti.sistemareservaturnos.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
//    @Modifying
//    @Query("UPDATE Paciente p SET p.dni = ?1 WHERE p.id = ?2")
//    void updateDni(Integer newDni, Long id);

    @Query("SELECT p FROM Paciente p WHERE p.dni = ?1")
    Paciente findPacienteByDNI(Integer dni);

}
