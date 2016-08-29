/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benhur
 */
@Entity
@Table(name = "goal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g"),
    @NamedQuery(name = "Goal.findByGid", query = "SELECT g FROM Goal g WHERE g.gid = :gid"),
    @NamedQuery(name = "Goal.findByPidTid", query = "SELECT g FROM Goal g WHERE g.pid.pid = :pid and g.tid.tid = :tid"),
    @NamedQuery(name = "Goal.findByPidGid", query = "SELECT g FROM Goal g WHERE g.pid.pid = :pid and g.tid = :tid"),
@NamedQuery(name = "Goal.findByPid", query = "SELECT g FROM Goal g WHERE g.pid.pid = :pid")})
public class Goal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "goal")
    private String goal;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne(optional = false)
    private Person pid;
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private Type tid;

    public Goal() {
    }

    public Goal(Integer gid) {
	this.gid = gid;
    }

    public Goal(Integer gid, String goal) {
	this.gid = gid;
	this.goal = goal;
    }

    public Integer getGid() {
	return gid;
    }

    public void setGid(Integer gid) {
	this.gid = gid;
    }

    public String getGoal() {
	return goal;
    }

    public void setGoal(String goal) {
	this.goal = goal;
    }

    public Person getPid() {
	return pid;
    }

    public void setPid(Person pid) {
	this.pid = pid;
    }

    public Type getTid() {
	return tid;
    }

    public void setTid(Type tid) {
	this.tid = tid;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (gid != null ? gid.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Goal)) {
	    return false;
	}
	Goal other = (Goal) object;
	if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "final1.domain.Goal[ gid=" + gid + " ]";
    }
    
}
