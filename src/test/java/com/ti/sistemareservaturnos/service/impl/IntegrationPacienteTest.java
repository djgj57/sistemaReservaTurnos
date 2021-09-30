package com.ti.sistemareservaturnos.service.impl;


import com.ti.sistemareservaturnos.model.Domicilio;
import com.ti.sistemareservaturnos.model.Paciente;
import com.ti.sistemareservaturnos.util.Jsons;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationPacienteTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    @Test
    public void cargarDataSet() throws Exception{
        Domicilio domicilio = new Domicilio(null, "Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.save(new Paciente(null, "Santiago", "Paz", "88888888",
                LocalDate.of(2020,12,12), domicilio, null));
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes" +
                                "/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(p)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertFalse((response.getResponse()).getContentAsString().isEmpty());
    }

    @Test
    public void listarPacientes() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

}
