package io.cloudsoft.brooklyn.webapp.example;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.apache.brooklyn.api.entity.Entity;
import org.apache.brooklyn.api.mgmt.ManagementContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

@Path("/")
public class ExampleResource {

    private static final Logger log = LoggerFactory.getLogger(ExampleResource.class);
    
    private @Context ContextResolver<ManagementContext> mgmt;
    
    public ManagementContext mgmt() {
        return Preconditions.checkNotNull(mgmt.getContext(ManagementContext.class), "mgmt");
    }

    @Path("/{entityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(
            @PathParam("entityId") final String entityId) {
        log.info("GET entityId={}", entityId);
        Entity entity = mgmt().getEntityManager().getEntity(entityId);
        if (entity == null) {
            throw new NoSuchElementException("Entity '"+entityId+"' not found");
        }
        return "GET for " + entityId + ": " + entity.toString();
    }

    @Path("/{entityId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(
            @PathParam("entityId") final String entityId,
            String body) {
        log.info("POST entityId={}, body={}", entityId, body);
        Entity entity = mgmt().getEntityManager().getEntity(entityId);
        if (entity == null) {
            throw new NoSuchElementException("Entity '"+entityId+"' not found");
        }
        return "POST for " + entityId + " ,body " + body + ": " + entity.toString();
    }
}
