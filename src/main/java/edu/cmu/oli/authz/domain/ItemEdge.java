package edu.cmu.oli.authz.domain;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Raphael Gachuhi
 */
@Entity
@Table(name = "item_edge")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ItemEdge.findAll", query = "SELECT i FROM ItemEdge i"),
        @NamedQuery(name = "ItemEdge.findByUniqueId", query = "SELECT i FROM ItemEdge i WHERE i.uniqueId = :uniqueId"),
        @NamedQuery(name = "ItemEdge.findByEdge", query = "SELECT a FROM ItemEdge a WHERE a.parent = :parent and a.child = :child"),
        @NamedQuery(name = "ItemEdge.findByCreated", query = "SELECT i FROM ItemEdge i WHERE i.created = :created")})
public class ItemEdge implements Serializable {
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
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Expose()
    @JoinColumn(name = "parent_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Item parent;

    @Expose()
    @JoinColumn(name = "child_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Item child;

    public ItemEdge() {
    }

    public ItemEdge(Item parentItem, Item childItem) {
        this.parent = parentItem;
        this.child = childItem;
        this.created = new Date();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public Item getChild() {
        return child;
    }

    public void setChild(Item child) {
        this.child = child;
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
        if (!(object instanceof ItemEdge)) {
            return false;
        }
        ItemEdge other = (ItemEdge) object;
        if ((this.uniqueId == null && other.uniqueId != null) || (this.uniqueId != null && !this.uniqueId.equals(other.uniqueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cmu.oli.model.ItemEdge[ uniqueId=" + uniqueId + " ]";
    }

}
