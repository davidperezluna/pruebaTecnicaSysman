package com.tecnica.dto;

import com.tecnica.entity.Ciudad;
import com.tecnica.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MaterialDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Date fechaCompra;
    private Date fechaVenta;
    private Estado estado;
    private Long idCiudad;
    private Ciudad ciudad;

}
