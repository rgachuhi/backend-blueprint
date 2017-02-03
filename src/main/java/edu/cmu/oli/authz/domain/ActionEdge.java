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
@Table(name = "action_edge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionEdge.findAll", query = "SELECT a FROM ActionEdge a"),
    @NamedQuery(name = "ActionEdge.findByUniqueId", query = "SELECT a FROM ActionEdge a WHERE a.uniqueId = :uniqueId"),
    @NamedQuery(name = "ActionEdge.findByEdge", query = "SELECT a FROM ActionEdge a WHERE a.parent = :parent and a.child = :child"),
    @NamedQuery(name = "ActionEdge.findByCreated", query = "SELECT a FROM ActionEdge a WHERE a.created = :created")})
public class ActionEdge implements Serializable {

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
    private Action parent;

    @Expose()
    @JoinColumn(name = "child_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Action child;

    public ActionEdge() {
    }

    public ActionEdge(Action parentAction, Action childAction) {
        this.parent = parentAction;
        this.child = childAction;
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

    public Action getParent() {
        return parent;
    }

    public void setParent(Action parent) {
        this.parent = parent;
    }

    public Action getChild() {
        return child;
    }

    public void setChild(Action child) {
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
        if (!(object instanceof ActionEdge)) {
            return false;
        }
        ActionEdge other = (ActionEdge) object;
        if ((this.uniqueId == null && other.uniqueId != null) || (this.uniqueId != null && !this.uniqueId.equals(other.uniqueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cmu.oli.model.ActionEdge[ uniqueId=" + uniqueId + " ]";
    }
    
}
