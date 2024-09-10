package com.tecnica.controller;

import com.tecnica.dto.FilterDto;
import com.tecnica.dto.MaterialDto;
import com.tecnica.entity.Ciudad;
import com.tecnica.services.MaterialService;
import com.tecnica.utils.GenericResponseDTO;
import com.tecnica.utils.UtilConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("material")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @GetMapping("/listar")
    public ResponseEntity<GenericResponseDTO> listar() throws IOException {
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                materialService.getAllMaterial(),
                true,
                UtilConstantes.RESPONSE_FIND,
                HttpStatus.OK,
                UtilConstantes.TITTLE_FIND);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<GenericResponseDTO> crearMaterial(@RequestBody MaterialDto mat){
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                materialService.createMaterial(mat),
                true,
                UtilConstantes.RESPONSE_CREATED,
                HttpStatus.OK,
                UtilConstantes.TITTLE_CREATED);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }

    @PutMapping("/editar")
    public ResponseEntity<GenericResponseDTO> editarMaterial(@RequestBody MaterialDto mat){
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                materialService.editMaterial(mat),
                true,
                UtilConstantes.RESPONSE_CREATED,
                HttpStatus.OK,
                UtilConstantes.TITTLE_CREATED);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }

    @PostMapping("/findByTipoAndFechaCompra")
    public ResponseEntity<GenericResponseDTO> getXByCategory(@RequestBody FilterDto filtro)  {
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                materialService.getAllByTipoAndFechaCompra(filtro.getTipo(),filtro.getFechaCompra()),
                true,
                UtilConstantes.RESPONSE_FIND,
                HttpStatus.OK,
                UtilConstantes.TITTLE_FIND);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }

    @PostMapping("/findByCiudad")
    public ResponseEntity<GenericResponseDTO> getByCiudad(@RequestBody FilterDto filtro)  {
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                materialService.getAllByCuidad(filtro.getIdCiudad()),
                true,
                UtilConstantes.RESPONSE_FIND,
                HttpStatus.OK,
                UtilConstantes.TITTLE_FIND);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }
}
