package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.idTest = :idTest")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_test")
    private Integer idTest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testIdPregunta")
    private List<Pregunta> preguntaList;

    public Test() {
    }

    public Test(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.Test[ idTest=" + idTest + " ]";
    }
    
}
