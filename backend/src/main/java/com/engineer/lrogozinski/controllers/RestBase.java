package com.engineer.lrogozinski.controllers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestBase {

    protected Response jsonResponse(Object o) {

        return Response.ok(o, MediaType.APPLICATION_JSON).build();
    }

    protected Response errorResponse(Object o, int status) {

        return Response.status(status).entity(o).build();
    }

}
