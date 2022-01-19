package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "credencial")
public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial")
    private Integer idCredencial;
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email_credencial", unique = true, nullable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pass_credencial", nullable = false)
    private String pass;
    
    @NotNull
    @Column(name = "activo_credencial", nullable = false, columnDefinition = "bit(1) default 1")
    private boolean enable;
    
    @ManyToMany
    private Set<Rol> roles = new HashSet<>();

    public void addRole(Rol rol) {
        this.roles.add(rol);
    }

    public Credencial(String email, String pass, Set<Rol> roles) {
        this.email = email;
        this.pass = pass;
        this.roles = roles;
    }
    
    
}
