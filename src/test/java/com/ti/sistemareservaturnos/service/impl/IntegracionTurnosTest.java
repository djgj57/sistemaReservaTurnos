package com.ti.sistemareservaturnos.service.impl;


import com.ti.sistemareservaturnos.model.Domicilio;
import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.model.Turno;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void cargarDataSet() throws Exception {
        Domicilio d = new Domicilio(null, "Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente(null, "Santiago", "Paz", "88888888",
                LocalDate.of(2020,12,12), d, null));
        Odontologo o = odontologoService.save(new Odontologo(null, "Santiago", "Paz", 3455647,
            null));
        Turno t = turnoService.save(new Turno(null, LocalDate.of(2020,9,9), LocalTime.of(5,30,0),
                o,p));
    }

    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}



