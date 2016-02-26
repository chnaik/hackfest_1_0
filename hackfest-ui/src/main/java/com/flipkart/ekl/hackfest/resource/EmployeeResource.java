package com.flipkart.ekl.hackfest.resource;

import com.flipkart.ekl.hackfest.core.HttpResponse;
import com.flipkart.ekl.hackfest.request.UpsertSkillRequest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by chaitanya.naik on 25/02/16.
 */

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @POST
    @Path("/skills")
    public Response upsertSkills(UpsertSkillRequest skillRequest) {
        System.out.println(skillRequest);
        Response.Status status = Response.Status.CREATED;
        return Response.status(status).entity(new HttpResponse(status.getStatusCode(), status.getReasonPhrase())).build();
    }


    @POST
    @Path("/v2/skills")
    public Response upsertSkillsV2(List<UpsertSkillRequest> skillRequestList) {
        for(UpsertSkillRequest skillRequest: skillRequestList)
            System.out.println(skillRequest);
        Response.Status status = Response.Status.CREATED;
        return Response.status(status).entity(new HttpResponse(status.getStatusCode(), status.getReasonPhrase())).build();
    }
}