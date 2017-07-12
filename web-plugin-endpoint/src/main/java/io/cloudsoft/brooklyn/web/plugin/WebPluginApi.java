/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.cloudsoft.brooklyn.web.plugin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;

import org.apache.brooklyn.api.mgmt.ManagementContext;

import com.google.common.base.Preconditions;

/**
 * Note: the endpoint is <host>/v1/whatever because it uses the brooklyn CXF bus which uses /v1
 */
@Path("/")
public class WebPluginApi {

    private @Context ContextResolver<ManagementContext> mgmt;
    
    public ManagementContext mgmt() {
        return Preconditions.checkNotNull(mgmt.getContext(ManagementContext.class), "mgmt");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{entityId}")
    public String endpoint(@PathParam("entityId") final String entityId) {
        return mgmt().getEntityManager().getEntity(entityId)+"";
    }

}
