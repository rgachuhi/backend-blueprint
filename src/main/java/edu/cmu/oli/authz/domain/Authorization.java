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
@Table(name = "authorization")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Authorization.findAll", query = "SELECT a FROM Authorization a"),
        @NamedQuery(name = "Authorization.findByUniqueId", query = "SELECT a FROM Authorization a WHERE a.uniqueId = :uniqueId"),
        @NamedQuery(name = "Authorization.findByCreated", query = "SELECT a FROM Authorization a WHERE a.created = :created"),
        @NamedQuery(name = "Authorization.findByAuth", query = "SELECT a FROM Authorization a WHERE a.actor = :actor AND a.action = :action AND a.item = :item"),
        @NamedQuery(name = "Authorization.findByAuthNames", query = "SELECT a FROM Authorization a WHERE a.actor.name = :actorName AND a.action.name = :actionName AND a.item.name = :itemName"),
        @NamedQuery(name = "Authorization.findByAuthUnexpired", query = "SELECT a FROM Authorization a WHERE a.actor = :actor AND a.action = :action AND a.item = :item AND (a.expiration IS NULL OR a.expiration > :expiration)"),
        @NamedQuery(name = "Authorization.findByActor", query = "SELECT a FROM Authorization a WHERE a.actor = :actor"),
        @NamedQuery(name = "Authorization.findByAction", query = "SELECT a FROM Authorization a WHERE a.action = :action"),
        @NamedQuery(name = "Authorization.findByItem", query = "SELECT a FROM Authorization a WHERE a.item = :item"),
        @NamedQuery(name = "Authorization.findByActorAction", query = "SELECT a FROM Authorization a WHERE a.actor = :actor AND a.action = :action"),
        @NamedQuery(name = "Authorization.findByExpiration", query = "SELECT a FROM Authorization a WHERE a.expiration = :expiration")})
public class Authorization implements Serializable {
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
    @Column(name = "expiration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    @Expose()
    @JoinColumn(name = "actor_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Actor actor;

    @Expose()
    @JoinColumn(name = "action_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Action action;

    @Expose()
    @JoinColumn(name = "item_id", referencedColumnName = "unique_id")
    @ManyToOne
    private Item item;

    public Authorization() {
    }

    public Authorization(Date expiration, Actor actor, Action action, Item item) {
        this.expiration = expiration;
        this.actor = actor;
        this.action = action;
        this.item = item;
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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        if (!(object instanceof Authorization)) {
            return false;
        }
        Authorization other = (Authorization) object;
        if ((this.uniqueId == null && other.uniqueId != null) || (this.uniqueId != null && !this.uniqueId.equals(other.uniqueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.cmu.oli.model.Authorization[ uniqueId=" + uniqueId + " ]";
    }

}
