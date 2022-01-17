package com.teamfaceless.facelessjobs.dtos.empresa;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaListadoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @NotEmpty
    private String nombreEmpresa;
    
    @Size(max = 45)
    private String nombreJuridicoEmpresa;
    
//    @Size(max = 200)
//    private String localidadEmpresa;
//    
//    private Integer empleadosEmpresa;
//    
//    private String provinciaEmpresa;
// 
//    private String sectorEmpresa;
    
}
