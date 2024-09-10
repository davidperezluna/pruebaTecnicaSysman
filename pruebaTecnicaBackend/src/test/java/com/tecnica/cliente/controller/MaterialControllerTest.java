package com.tecnica.cliente.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnica.controller.MaterialController;
import com.tecnica.dto.FilterDto;
import com.tecnica.dto.MaterialDto;
import com.tecnica.services.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(MaterialController.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void listar() throws Exception {
        when(materialService.getAllMaterial()).thenReturn(Collections.emptyList());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/material/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    void testCrearMaterial() throws Exception {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(2l);
        materialDto.setIdCiudad(1l);
        materialDto.setNombre("Material1");
        materialDto.setDescripcion("descripción del material");
        materialDto.setTipo("tipo de material");
        materialDto.setFechaCompra(new Date());
        materialDto.setFechaVenta(new Date());

        when(materialService.createMaterial(any(MaterialDto.class))).thenReturn(materialDto);

        String inputJson = new ObjectMapper().writeValueAsString(materialDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/material/crear")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        assertTrue(jsonResponse.contains("\"id\":2"));
        assertTrue(jsonResponse.contains("\"nombre\":\"Material1\""));
    }

    @Test
    void testActualizarMaterial() throws Exception {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(2l);
        materialDto.setIdCiudad(1l);
        materialDto.setNombre("Material1");
        materialDto.setDescripcion("descripción del material");
        materialDto.setTipo("tipo de material");
        materialDto.setFechaCompra(new Date());
        materialDto.setFechaVenta(new Date());

        when(materialService.editMaterial(any(MaterialDto.class))).thenReturn(materialDto);

        String inputJson = new ObjectMapper().writeValueAsString(materialDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/material/editar")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        assertTrue(jsonResponse.contains("\"id\":2"));
        assertTrue(jsonResponse.contains("\"nombre\":\"Material1\""));
    }

    @Test
    void testBuscarMaterialPorTipoYFecha() throws Exception {

        FilterDto filtro = new FilterDto();
        filtro.setFechaCompra(new Date());
        filtro.setTipo("material1");

        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(2l);
        materialDto.setIdCiudad(1l);
        materialDto.setNombre("Material1");
        materialDto.setDescripcion("descripción del material");
        materialDto.setTipo("material1");
        materialDto.setFechaCompra(new Date());
        materialDto.setFechaVenta(new Date());

        List<MaterialDto>listMat = new ArrayList<>();
        listMat.add(materialDto);

        when(materialService.getAllByTipoAndFechaCompra(filtro.getTipo(),filtro.getFechaCompra())).thenReturn(listMat);
        String inputJson = new ObjectMapper().writeValueAsString(materialDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/material/findByTipoAndFechaCompra")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        assertTrue(jsonResponse.contains("\"id\":2"));
        assertTrue(jsonResponse.contains("\"tipo\":\"material1\""));
    }



}
