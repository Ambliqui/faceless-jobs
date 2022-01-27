package com.teamfaceless.facelessjobs.dtos.filtro;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.model.Provincia;
import com.teamfaceless.facelessjobs.model.SectorLaboral;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filtro {
 private String titulo;
private String descripcion;
private String provincia;
private String sector;
private Integer salarioMinimo;
private Integer salarioMaximo;
private Integer nPagina;

}
