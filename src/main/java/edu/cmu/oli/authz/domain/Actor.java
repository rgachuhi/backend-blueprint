package edu.cmu.oli.authz.domain;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Raphael Gachuhi
 */
@Entity
@Table(name = "actor")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
        @NamedQuery(name = "Actor.findByUniqueId", query = "SELECT a FROM Actor a WHERE a.uniqueId = :uniqueId"),
        @NamedQuery(name = "Actor.findByName", query = "SELECT a FROM Actor a WHERE a.name = :name"),
        @NamedQuery(name = "Actor.findByDescription", query = "SELECT a FROM Actor a WHERE a.description = :description"),
        @NamedQuery(name = "Actor.findByCreated", query = "SELECT a FROM Actor a WHERE a.created = :created")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose()
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "unique_id")
    private String uniqueId;

    @Expose()
    @Size(max = 250)
    @Column(name = "name")
    private String name;

    @Expose()
    @Size(max = 250)
    @Column(name = "description")
    private String description;

    @Expose()
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(mappedBy = "actor")
    private Collection<Authorization> authorizationCollection;
    @OneToMany(mappedBy = "parent")
    private Collection<ActorEdge> parentEdges;
    @OneToMany(mappedBy = "child")
    private Collection<ActorEdge> childEdges;

    public Actor() {

    }

    public Actor(String name, String description) {
        this.name = name;
        this.description = description;
        this.created = new Date();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlTransient
    public Collection<Authorization> getAuthorizationCollection() {
        return authorizationCollection;
    }

    public void setAuthorizationCollection(Collection<Authorization> authorizationCollection) {
        this.authorizationCollection = authorizationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniqueId != null ? uniqueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.uniqueId == null && other.uniqueId != null) || (this.uniqueId != null && !this.uniqueId.equals(other.uniqueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cmu.oli.model.Actor[ uniqueId=" + uniqueId + " ]";
    }

    @XmlTransient
    public Collection<ActorEdge> getParentEdges() {
        return parentEdges;
    }

    public void setParentEdges(Collection<ActorEdge> parentEdges) {
        this.parentEdges = parentEdges;
    }

    @XmlTransient
    public Collection<ActorEdge> getChildEdges() {
        return childEdges;
    }

    public void setChildEdges(Collection<ActorEdge> childEdges) {
        this.childEdges = childEdges;
    }

}
