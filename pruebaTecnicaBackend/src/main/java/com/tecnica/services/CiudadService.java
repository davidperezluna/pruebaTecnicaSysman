package com.tecnica.services;

import com.tecnica.dto.CiudadDto;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    List<CiudadDto> getAllCiudades();
    CiudadDto getById(Long id);
    CiudadDto guardar(CiudadDto ciudadDto);
}
