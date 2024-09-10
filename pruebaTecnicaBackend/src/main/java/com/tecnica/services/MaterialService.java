
package com.tecnica.services;

import com.tecnica.dto.MaterialDto;
import com.tecnica.entity.Ciudad;

import java.util.Date;
import java.util.List;

public interface MaterialService {
    List<MaterialDto> getAllMaterial();
    List<MaterialDto> getAllByTipoAndFechaCompra(String tipo, Date fechaCompra);
    List<MaterialDto> getAllByCuidad(Long idCiudad);
    MaterialDto createMaterial(MaterialDto materialDto);
    MaterialDto editMaterial(MaterialDto materialDto);

}
