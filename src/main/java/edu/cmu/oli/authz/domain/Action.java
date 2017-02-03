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
@Table(name = "action")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
        @NamedQuery(name = "Action.findByUniqueId", query = "SELECT a FROM Action a WHERE a.uniqueId = :uniqueId"),
        @NamedQuery(name = "Action.findByName", query = "SELECT a FROM Action a WHERE a.name = :name"),
        @NamedQuery(name = "Action.findByDescription", query = "SELECT a FROM Action a WHERE a.description = :description"),
        @NamedQuery(name = "Action.findByCreated", query = "SELECT a FROM Action a WHERE a.created = :created")})
public class Action implements Serializable {

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

    @Size(max = 250)
    @Column(name = "description")
    @Expose()
    private String description;

    @Column(name = "created")
    @Expose()
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(mappedBy = "action")
    private Collection<Authorization> authorizationCollection;

    @OneToMany(mappedBy = "parent")
    private Collection<ActionEdge> parentEdges;

    @OneToMany(mappedBy = "child")
    private Collection<ActionEdge> childEdges;

    public Action() {
    }

    public Action(String name, String description) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (!uniqueId.equals(action.uniqueId)) return false;
        return name.equals(action.name);
    }

    @Override
    public int hashCode() {
        int result = uniqueId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Action[ uniqueId=" + uniqueId + " ]";
    }

    @XmlTransient
    public Collection<ActionEdge> getParentEdges() {
        return parentEdges;
    }

    public void setParentEdges(Collection<ActionEdge> parentEdges) {
        this.parentEdges = parentEdges;
    }

    @XmlTransient
    public Collection<ActionEdge> getChildEdges() {
        return childEdges;
    }

    public void setChildEdges(Collection<ActionEdge> childEdges) {
        this.childEdges = childEdges;
    }
}
