package com.ti.sistemareservaturnos.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "turnos")
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence")
    private Long id;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    public Turno(Long id, LocalDate fechaTurno, LocalTime horaTurno, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Turno() {

    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fechaTurno=" + fechaTurno +
                ", horaTurno=" + horaTurno +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                '}';
    }
}
