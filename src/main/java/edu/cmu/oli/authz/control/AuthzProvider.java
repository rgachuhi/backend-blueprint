package edu.cmu.oli.authz.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.cmu.oli.authz.domain.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Raphael Gachuhi
 */
@Stateless
public class AuthzProvider {
    @PersistenceContext
    EntityManager em;

    @Inject
    Logger log;

    public String process(String entity, String id, JsonObject body, String cmd) {
        switch (entity.toLowerCase()) {
            case "action":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "Action.findAll" : "Action.findByUniqueId",
                            new TypeToken<ArrayList<Action>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    String name = body.getString("name");
                    String description = body.getString("description");
                    if (id != null) {
                        Action up = (Action) findId("Action.findByUniqueId", id);
                        if (up != null) {
                            up.setName(name);
                            up.setDescription(description);
                            em.merge(up);
                        }
                    } else {
                        Action action = new Action(name, description);
                        em.persist(action);
                    }
                }
            case "action_edge":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "ActionEdge.findAll" : "ActionEdge.findByUniqueId",
                            new TypeToken<ArrayList<ActionEdge>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    Action parentAction = (Action)findId("Action.findByUniqueId", body.getString("parent"));
                    Action childAction = (Action)findId("Action.findByUniqueId", body.getString("child"));
                    if (id != null) {
                        ActionEdge up = (ActionEdge)findId("ActionEdge.findByUniqueId", id);
                        if (up != null) {
                            up.setParent(parentAction);
                            up.setChild(childAction);
                            em.merge(up);
                        }
                    }else {
                        ActionEdge newActionEdge = new ActionEdge(parentAction, childAction);
                        em.persist(newActionEdge);
                    }
                }
            case "actor":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "Actor.findAll" : "Actor.findByUniqueId",
                            new TypeToken<ArrayList<Actor>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    String name = body.getString("name");
                    String description = body.getString("description");
                    if (id != null) {
                        Actor actor = (Actor)findId("Actor.findByUniqueId", id);
                        if (actor != null) {
                            actor.setName(name);
                            actor.setDescription(description);
                            em.merge(actor);
                        }
                    }else {
                        Actor newActor = new Actor(name, description);
                        em.persist(newActor);
                    }
                }
            case "actor_edge":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "ActorEdge.findAll" : "ActorEdge.findByUniqueId",
                            new TypeToken<ArrayList<ActorEdge>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    Actor parentActor = (Actor)findId("Actor.findByUniqueId", body.getString("parent"));
                    Actor childActor = (Actor)findId("Actor.findByUniqueId", body.getString("child"));
                    if (id != null) {
                        ActorEdge up = (ActorEdge)findId("ActorEdge.findByUniqueId", id);
                        if (up != null) {
                            up.setParent(parentActor);
                            up.setChild(childActor);
                            em.merge(up);
                        }
                    }else {
                        ActorEdge newActorEdge = new ActorEdge(parentActor, parentActor);
                        em.persist(newActorEdge);
                    }
                }
            case "authorization":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "Authorization.findAll" : "Authorization.findByUniqueId",
                            new TypeToken<ArrayList<Authorization>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    Actor actorLocal = (Actor)findId("Actor.findByUniqueId", body.getString("actor"));
                    Action actionLocal = (Action)findId("Action.findByUniqueId", body.getString("action"));
                    Item itemLocal = (Item)findId("Item.findByUniqueId", body.getString("item"));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date expiration = null;
                    try {
                        expiration = dateFormat.parse(body.getString("expiration"));
                    } catch (ParseException e) {
                        throw new RuntimeException("Error pursing expiration date");
                    }
                    if (id != null) {
                        Authorization up = (Authorization)findId("Authorization.findByUniqueId", id);
                        if (up != null) {
                            up.setActor(actorLocal);
                            up.setAction(actionLocal);
                            up.setItem(itemLocal);
                            em.merge(up);
                        }
                    }else {
                        Authorization newAuthorization = new Authorization(expiration, actorLocal, actionLocal, itemLocal);
                        em.persist(newAuthorization);
                    }
                }
            case "item":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "Item.findAll" : "Item.findByUniqueId",
                            new TypeToken<ArrayList<Item>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    String name = body.getString("name");
                    String description = body.getString("description");
                    if (id != null) {
                        Item item = (Item)findId("Item.findByUniqueId", id);
                        if (item != null) {
                            item.setName(name);
                            item.setDescription(description);
                            em.merge(item);
                        }
                    }else {
                        Item newItem = new Item(name, description);
                        em.persist(newItem);
                    }
                }
            case "item_edge":
                if (cmd.equals("find") || cmd.equals("delete")) {
                    return find(id == null ? "ItemEdge.findAll" : "ItemEdge.findByUniqueId",
                            new TypeToken<ArrayList<ItemEdge>>() {
                            }.getType(), id, cmd.equals("delete"));
                } else if (cmd.equals("post") || cmd.equals("put")) {
                    Item parentItem = (Item)findId("Item.findByUniqueId", body.getString("parent"));
                    Item childItem = (Item)findId("Item.findByUniqueId", body.getString("child"));
                    if (id != null) {
                        ItemEdge up = (ItemEdge)findId("ItemEdge.findByUniqueId", id);
                        if (up != null) {
                            up.setParent(parentItem);
                            up.setChild(childItem);
                            em.merge(up);
                        }
                    }else {
                        ItemEdge newActorEdge = new ItemEdge(parentItem, childItem);
                        em.persist(newActorEdge);
                    }
                }
        }
        throw new RuntimeException("Error: Entity type not supported");
    }

    public String isAuthorized(String actor, String action, String item){
        TypedQuery<Authorization> q = em.createNamedQuery("Authorization.findByAuthNames", Authorization.class);
        q.setParameter("actorName", actor);
        q.setParameter("actionName", action);
        q.setParameter("itemName", item);
        List<Authorization> results = q.getResultList();
        if(results.isEmpty()){
            return "false";
        }
        return "true";
    }

    private String find(String query, Type type, String id, boolean delete) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Query q = em.createNamedQuery(query);
        if (id == null) {
            List results = q.getResultList();
            return gson.toJson(results, type);
        } else {
            q.setParameter("uniqueId", id);
            Object singleResult = q.getSingleResult();
            if (singleResult != null && delete) {
                em.remove(singleResult);
                return "deleted";
            }
            List results = new ArrayList();
            results.add(singleResult);
            return gson.toJson(results, type);
        }
    }

    private Object findId(String query, String id) {
        Query q = em.createNamedQuery(query);
        q.setParameter("uniqueId", id);
        return q.getSingleResult();
    }
}
