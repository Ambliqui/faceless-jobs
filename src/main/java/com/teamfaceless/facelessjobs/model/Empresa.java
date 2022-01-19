package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.teamfaceless.facelessjobs.enums.Provincias;
import com.teamfaceless.facelessjobs.validations.CifRepetido.CIFExist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @Size(max = 100)
    @Column(name = "logo_empresa")
    private String logoEmpresa;
    
    @NotEmpty
    @Pattern(regexp = "[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}")
    @CIFExist
    @Column(name = "CIF_empresa", nullable = false, unique = true)
    private String cIFempresa;
    
    @NotEmpty
    @Size(max = 100)
    @Column(name = "nombre_empresa" , nullable = false)
    private String nombreEmpresa;
    
    @NotEmpty
    @Size(max = 45)
    @Column(name = "nombre_juridico_empresa" , nullable = false)
    private String nombreJuridicoEmpresa;
    
    @NotEmpty
    @Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}")
    @Column(name = "telefono_empresa")
    private String telefonoEmpresa;
    
    @Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}|^$")//Numero o Empty
    @Column(name = "whatsapp_empresa")
    private String whatsappEmpresa;
    
    @NotEmpty
    @Size(max = 200)
    @Column(name = "direccion_empresa")
    private String direccionEmpresa;
    
    @Column(name = "provincia_empresa_e")
    private Provincias provinciaEmpresaE;
    
    @NotEmpty
    @Size(max = 200)
    @Column(name = "localidad_empresa")
    private String localidadEmpresa;
    
    @Positive
    @Min(value = 1)
    @Column(name = "empleados_empresa")
    private Integer empleadosEmpresa;
    
    @Singular
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empresa_oferta",referencedColumnName = "id_empresa")
    private List<OfertaEmpleo> ofertasEmpleos;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credencial_id_credencial", referencedColumnName = "id_credencial")
    private Credencial credencial;
    
    @Enumerated
    @Column(name = "provincia_empresa")
    private Provincias provinciaEmpresa;
    
    @JoinColumn(name = "sector_empresa", referencedColumnName = "id_sector_laboral")
    @ManyToOne
    private SectorLaboral sectorEmpresa;
    
    public void addOfertaEmpleo(OfertaEmpleo ofertaEmpleo) {
    	this.ofertasEmpleos.add(ofertaEmpleo);
    }
}
