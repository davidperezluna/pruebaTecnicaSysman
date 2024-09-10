package com.tecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterDto {
    private String tipo;
    private Long idCiudad;
    private Date fechaCompra;
}
