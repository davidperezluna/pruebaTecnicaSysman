package com.tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CiudadDto {

    private Long id;
    private String codigo;
    private String nombre;
    private DepartamentoDto departamento;
}
