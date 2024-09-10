package com.tecnica.entity;

import com.tecnica.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Material {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String Descripcion;
    private String tipo;
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    private Estado estado;
    @ManyToOne
    private Ciudad ciudad;
}


