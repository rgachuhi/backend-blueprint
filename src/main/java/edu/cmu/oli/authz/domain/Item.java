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
@Table(name = "item")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
        @NamedQuery(name = "Item.findByUniqueId", query = "SELECT i FROM Item i WHERE i.uniqueId = :uniqueId"),
        @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
        @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item i WHERE i.description = :description"),
        @NamedQuery(name = "Item.findByCreated", query = "SELECT i FROM Item i WHERE i.created = :created")})
public class Item implements Serializable {

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

    @OneToMany(mappedBy = "item")
    private Collection<Authorization> authorizationCollection;
    @OneToMany(mappedBy = "parent")
    private Collection<ItemEdge> parentEdges;
    @OneToMany(mappedBy = "child")
    private Collection<ItemEdge> childEdges;

    public Item() {
    }

    public Item(String name, String description) {
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.uniqueId == null && other.uniqueId != null) || (this.uniqueId != null && !this.uniqueId.equals(other.uniqueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cmu.oli.model.Item[ uniqueId=" + uniqueId + " ]";
    }

    @XmlTransient
    public Collection<ItemEdge> getParentEdges() {
        return parentEdges;
    }

    public void setParentEdges(Collection<ItemEdge> parentEdges) {
        this.parentEdges = parentEdges;
    }

    @XmlTransient
    public Collection<ItemEdge> getChildEdges() {
        return childEdges;
    }

    public void setChildEdges(Collection<ItemEdge> childEdges) {
        this.childEdges = childEdges;
    }

}
