package com.teamfaceless.facelessjobs.dtos.empresa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teamfaceless.facelessjobs.model.Rol;

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

    @Email
    private String emailEmpresa;
    
    @Email
    private String confirmEmailEmpresa;
    
    @NotEmpty
    private String passEmpresa;
    
    @NotEmpty
    private String confirmPassEmpresa;
    
    @Size(max = 45)
    @Pattern(regexp = "[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}")
    private String cIFempresa;
    
    @Size(max = 45)
    @NotEmpty
    private String nombreEmpresa;
    
    @NotEmpty
    @Size(max = 45)
    private String nombreJuridicoEmpresa;
    
    @Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}")
    private String telefonoEmpresa;
    
    @Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}|^$")//Numero o Empty
    private String whatsappEmpresa;
    
    @NotEmpty
    @Size(max = 200)
    private String direccionEmpresa;
    
    @NotEmpty
    @Size(max = 200)
    private String localidadEmpresa;
    
    @NotNull
    @Min(1)
    private Integer empleadosEmpresa;
    
    @NotNull
    private Integer provinciaEmpresa;
 
    @NotNull
    private Integer sectorEmpresa;

    private Set<Rol> roles = new HashSet<>();
    
    private Boolean enable = true;
    
}
