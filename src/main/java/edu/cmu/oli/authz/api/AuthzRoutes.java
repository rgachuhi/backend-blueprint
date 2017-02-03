package edu.cmu.oli.authz.api;

import edu.cmu.oli.authz.control.AuthzProvider;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Raphael Gachuhi
 */
@Stateless
@Path("authz")
public class AuthzRoutes {

    @Inject
    Logger log;

    @Inject
    private AuthzProvider authzProvider;

    @Resource
    private ManagedExecutorService mes;

    @GET
    @Path("get/{entity}")
    public void getAll(@Suspended AsyncResponse response, @PathParam("entity") String entity) {
        CompletableFuture.supplyAsync(() -> authzProvider.process(entity, null, null, "find"), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    @GET
    @Path("get/{entity}/{id}")
    public void getById(@Suspended AsyncResponse response, @PathParam("entity") String entity,
                        @PathParam("id") String id) {
        CompletableFuture.supplyAsync(() -> authzProvider.process(entity, id, null, "find"), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    @DELETE
    @Path("delete/{entity}/{id}")
    public void deleteById(@Suspended AsyncResponse response, @PathParam("entity") String entity,
                           @PathParam("id") String id) {
        CompletableFuture.supplyAsync(() -> authzProvider.process(entity, id, null, "delete"), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    @POST
    @Path("post/{entity}")
    public void post(@Suspended AsyncResponse response, @PathParam("entity") String entity,
                     JsonObject body) {
        CompletableFuture.supplyAsync(() -> authzProvider.process(entity, null, body, "post"), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    @PUT
    @Path("put/{entity}/{id}")
    public void putById(@Suspended AsyncResponse response, @PathParam("entity") String entity,
                        @PathParam("id") String id,
                        JsonObject body) {
        CompletableFuture.supplyAsync(() -> authzProvider.process(entity, id, body, "put"), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    @GET
    @Path("authorize/{actor}/{action}/{item}")
    public void isAuthorized(@Suspended AsyncResponse response, @PathParam("actor") String actor,
                             @PathParam("action") String action, @PathParam("item") String item) {
        CompletableFuture.supplyAsync(() -> authzProvider.isAuthorized(actor, action, item), mes).exceptionally(this::handelExceptions).thenAccept(response::resume);
    }

    // Error handler
    private String handelExceptions(Throwable t) {
        String message = t.toString();
        log.log(Level.INFO, message);
        return message;
    }

}
