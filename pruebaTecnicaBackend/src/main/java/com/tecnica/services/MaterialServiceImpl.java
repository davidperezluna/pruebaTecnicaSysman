package com.tecnica.services;

import com.tecnica.dto.MaterialDto;
import com.tecnica.entity.Ciudad;
import com.tecnica.entity.Material;
import com.tecnica.manager.Log;
import com.tecnica.mapper.GenericMapper;
import com.tecnica.messages.ExceptionMessages;
import com.tecnica.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private Log log;

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<MaterialDto> getAllMaterial() {
        log.warm("buscado listado de todos los materiales");
        List<MaterialDto> materialesDto = GenericMapper.mapList(materialRepository.findAll(),MaterialDto.class);
        return materialesDto;
    }

    @Override
    public List<MaterialDto> getAllByTipoAndFechaCompra(String tipo, Date fechaCompra) {
        log.warm("buscado listado de todos los materiales por tipo y fecha de compra");
        List<MaterialDto> materialesDto = GenericMapper.mapList(materialRepository.findAllByTipoAndFechaCompra(tipo,fechaCompra),MaterialDto.class);
        return materialesDto;
    }

    @Override
    public List<MaterialDto> getAllByCuidad(Long idCiudad) {
        log.warm("buscado listado de todos los materiales por Ciudad");
        Ciudad ciudad = new Ciudad();
        ciudad.setId(idCiudad);
        List<MaterialDto> materialesDto = GenericMapper.mapList(materialRepository.findAllByCiudad(ciudad),MaterialDto.class);
        return materialesDto;
    }

    @Override
    public MaterialDto createMaterial(MaterialDto materialDto) {
        log.warm("Guardando Material");
        Material mat = GenericMapper.map(materialDto,Material.class);
        Ciudad ciudad = new Ciudad();
        ciudad.setId(materialDto.getIdCiudad());
        mat.setCiudad(ciudad);
        Material materialGuardado = materialRepository.save(mat);
        if(mat != null){
            return GenericMapper.map(materialGuardado,MaterialDto.class);
        }else{
            String mensajeError = ExceptionMessages.ERROR203.getMensajeConParametros();
            log.error(mensajeError);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensajeError);
        }
    }

    @Override
    public MaterialDto editMaterial(MaterialDto materialDto) {
        log.info("Actualizando material", materialDto.getId().toString());
        Optional<Material> matOptional = materialRepository.findById(materialDto.getId());
        if(matOptional.isPresent()) {
            Material matActualizado = GenericMapper.map(materialDto, Material.class);
            Ciudad ciudad = new Ciudad();
            ciudad.setId(materialDto.getIdCiudad());
            matActualizado.setCiudad(ciudad);
            materialRepository.save(matActualizado);
            log.info("material actualizado exitosamente");
            return GenericMapper.map(matActualizado,MaterialDto.class);
        }
        String mensajeError = ExceptionMessages.ERROR203.getMensajeConParametros();
        log.error(mensajeError);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensajeError);
    }
}
