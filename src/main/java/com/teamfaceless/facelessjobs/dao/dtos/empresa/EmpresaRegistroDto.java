package com.teamfaceless.facelessjobs.dao.dtos.empresa;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaRegistroDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emailEmpresa;
    
    private String confirmEmailEmpresa;
    
    private String passEmpresa;
    
    private String confirmPassEmpresa;
    
    @Size(max = 45)
    @NotEmpty(message = "CIF cannot be null")
    private String cIFempresa;
    
    @Size(max = 45)
    @NotEmpty(message = "Name cannot be null")
    private String nombreEmpresa;
    
    @Size(max = 45)
    private String nombreJuridicoEmpresa;
    
    @Size(max = 14)
    private String telefonoEmpresa;
    
    @Size(max = 45)
    private String whatsappEmpresa;
    
    @Size(max = 200)
    private String direccionEmpresa;
    
    @Size(max = 200)
    private String localidadEmpresa;
    
    private Integer empleadosEmpresa;
    
    private Integer provinciaEmpresa;
 
    private Integer sectorEmpresa;
    
}
