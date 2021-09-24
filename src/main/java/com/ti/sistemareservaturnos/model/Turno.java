package com.ti.sistemareservaturnos.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

}
