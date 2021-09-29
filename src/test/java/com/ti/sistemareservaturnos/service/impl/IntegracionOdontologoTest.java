package com.ti.sistemareservaturnos.service.impl;

import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.util.Jsons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters= false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegracionOdontologoTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void registrarOdontologo() throws Exception {
        Odontologo od = new Odontologo(null, "Carlos","Montoya",123,null);
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos" +
                                "/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(od)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @Order(2)
    public void ObtenerOdontologos() throws Exception {
        this.registrarOdontologo();
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());


    }
}
