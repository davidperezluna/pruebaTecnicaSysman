package com.tecnica.services;

import com.tecnica.dto.CiudadDto;
import com.tecnica.dto.MaterialDto;
import com.tecnica.entity.Ciudad;
import com.tecnica.entity.Departamento;
import com.tecnica.manager.Log;
import com.tecnica.mapper.GenericMapper;
import com.tecnica.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadSericeImpl implements CiudadService{

    @Autowired
    private Log log;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<CiudadDto> getAllCiudades() {
        log.warm("buscado listado de todos los ciudades");
        List<CiudadDto> ciudadesDto = GenericMapper.mapList(ciudadRepository.findAll(),CiudadDto.class);
        return ciudadesDto;
    }

    @Override
    public CiudadDto getById(Long id) {
        Ciudad ciudad =  ciudadRepository.findById(id).orElse(null);
        return GenericMapper.map(ciudad, CiudadDto.class);
    }

    @Override
    public CiudadDto guardar(CiudadDto ciudadDto) {
        log.warm("Guardando ciudad");
        Departamento dep = new Departamento();
        dep.setId(1L);
        Ciudad ciudad = GenericMapper.map(ciudadDto, Ciudad.class);
        ciudad.setDepartamento(dep);
        ciudadRepository.save(ciudad);
        return GenericMapper.map(ciudad, CiudadDto.class);
    }
}
