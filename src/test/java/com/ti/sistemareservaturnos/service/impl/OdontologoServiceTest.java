package com.ti.sistemareservaturnos.service.impl;

import com.ti.sistemareservaturnos.model.Odontologo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void guardarOdontologo() {

        Odontologo odontologoTest = new Odontologo(null, "Carlos","Montoya",123,null);
        Odontologo odontologoRespuesta =  odontologoService.save(odontologoTest);
        assertEquals(odontologoRespuesta.getMatricula(), odontologoTest.getMatricula());
    }
}