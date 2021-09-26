package com.example.clinicaDemo;

import com.ti.sistemareservaturnos.model.Odontologo;
import com.ti.sistemareservaturnos.service.impl.OdontologoService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
public class OdontologoServiceTests {


    static OdontologoService odontologoService;

    @BeforeClass
    public static void cargarDataSet() {
        Odontologo odontologo = new Odontologo(null, "Mario", "lopez", 453, null);
        odontologoService.save(odontologo);


    }

    @Test
    public void guardarOdontologo() {
        Odontologo odontologo = odontologoService.save(new Odontologo(null, "Mario", "lopez", 453,
                null));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void eliminarPacienteTest() {
        odontologoService.delete(1L);
        Assert.assertTrue(odontologoService.findById(1L).isEmpty());

    }

    @Test
    public void traerTodos() {
        List<Odontologo> odontologos = odontologoService.findAll();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() == 1);
        System.out.println(odontologos);
    }

}
