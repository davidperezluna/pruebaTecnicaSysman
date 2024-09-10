package com.tecnica.cliente.service;

import com.tecnica.dto.FilterDto;
import com.tecnica.dto.MaterialDto;
import com.tecnica.entity.Ciudad;
import com.tecnica.entity.Departamento;
import com.tecnica.entity.Material;
import com.tecnica.enums.Estado;
import com.tecnica.manager.Log;
import com.tecnica.repository.MaterialRepository;
import com.tecnica.services.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(MockitoExtension.class)
public class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private Log log;

    @InjectMocks
    private MaterialServiceImpl materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosLosMateriales() {
        List<Material> materiales = Arrays.asList(
                new Material(1L, "mat1", "des","tipoA", new Date(),new Date(), Estado.Activo,new Ciudad(1l,"001","pasto",(new Departamento(1l,"001","Nariño")))),
                new Material(2L, "mat2", "des2","tipoB", new Date(),new Date(),Estado.Activo,new Ciudad(1l,"001","pasto",(new Departamento(1l,"001","Nariño"))))
        );
        when(materialRepository.findAll()).thenReturn(materiales);

        List<MaterialDto> clienteDtoList = materialService.getAllMaterial();


        assertEquals(2, clienteDtoList.size());
        assertEquals("mat1", clienteDtoList.get(0).getNombre());
        assertEquals("mat2", clienteDtoList.get(1).getNombre());
    }

    @Test
    void testObtenerPorTipoYFecha() {
        Date date = new Date();
        List<Material> materiales = Arrays.asList(
                new Material(1L, "mat1", "des","tipoA", date,date, Estado.Activo,new Ciudad(1l,"001","pasto",(new Departamento(1l,"001","Nariño"))))
        );
        when(materialRepository.findAllByTipoAndFechaCompra("tipoA",date)).thenReturn(materiales);

        List<MaterialDto> clienteDtoList = materialService.getAllByTipoAndFechaCompra("tipoA",date);
        assertEquals(1, clienteDtoList.size());
        assertEquals("mat1", clienteDtoList.get(0).getNombre());
    }
}
