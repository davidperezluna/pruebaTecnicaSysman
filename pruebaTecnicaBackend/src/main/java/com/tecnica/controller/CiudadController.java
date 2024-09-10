package com.tecnica.controller;

import com.tecnica.dto.CiudadDto;
import com.tecnica.dto.MaterialDto;
import com.tecnica.services.CiudadService;
import com.tecnica.services.MaterialService;
import com.tecnica.utils.GenericResponseDTO;
import com.tecnica.utils.UtilConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("ciudad")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/listar")
    public ResponseEntity<GenericResponseDTO> listar() {
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                ciudadService.getAllCiudades(),
                true,
                UtilConstantes.RESPONSE_FIND,
                HttpStatus.OK,
                UtilConstantes.TITTLE_FIND);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<GenericResponseDTO> crearMaterial(@RequestBody CiudadDto ciudadDto){
        GenericResponseDTO genericResponse = new GenericResponseDTO(
                ciudadService.guardar(ciudadDto),
                true,
                UtilConstantes.RESPONSE_CREATED,
                HttpStatus.OK,
                UtilConstantes.TITTLE_CREATED);
        return new ResponseEntity<GenericResponseDTO>(genericResponse, HttpStatus.OK);
    }
}
