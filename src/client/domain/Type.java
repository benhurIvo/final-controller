/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author benhur
 */
@Entity
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByTid", query = "SELECT t FROM Type t WHERE t.tid = :tid"),
    @NamedQuery(name = "Type.findByType", query = "SELECT t FROM Type t WHERE t.type = :type"),
    @NamedQuery(name = "Type.findByMeasure", query = "SELECT t FROM Type t WHERE t.measure = :measure")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "measure")
    private String measure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid")
    private Collection<Healthprofile> healthprofileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid")
    private Collection<Goal> goalCollection;

    public Type() {
    }

    public Type(Integer tid) {
	this.tid = tid;
    }

    public Type(Integer tid, String type, String measure) {
	this.tid = tid;
	this.type = type;
	this.measure = measure;
    }

    public Integer getTid() {
	return tid;
    }

    public void setTid(Integer tid) {
	this.tid = tid;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getMeasure() {
	return measure;
    }

    public void setMeasure(String measure) {
	this.measure = measure;
    }

    @XmlTransient
    public Collection<Healthprofile> getHealthprofileCollection() {
	return healthprofileCollection;
    }

    public void setHealthprofileCollection(Collection<Healthprofile> healthprofileCollection) {
	this.healthprofileCollection = healthprofileCollection;
    }

    @XmlTransient
    public Collection<Goal> getGoalCollection() {
	return goalCollection;
    }

    public void setGoalCollection(Collection<Goal> goalCollection) {
	this.goalCollection = goalCollection;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (tid != null ? tid.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Type)) {
	    return false;
	}
	Type other = (Type) object;
	if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "final1.domain.Type[ tid=" + tid + " ]";
    }
    
}
