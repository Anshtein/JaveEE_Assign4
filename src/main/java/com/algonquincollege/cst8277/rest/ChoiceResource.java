package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.rest.ChoiceConstants.CHOICE_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.CHOICE_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.CHOICE_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_404_DESC;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.ChoiceBean;
import com.algonquincollege.cst8277.models.Choice;
import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.Product;

@Path(CHOICE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChoiceResource {
    
    @EJB
    protected ChoiceBean choiceBean;

    @Inject
    protected SecurityContext sc;
    
    @GET
    @Operation(description = GET_CHOICE_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CHOICE_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CHOICE_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CHOICE_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getChoices() {
        Response response = null;

        /*
        if (!sc.isCallerInRole(ADMIN_ROLENAME)) {
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_JSON_MSG).build();
        }
        else {
        */
            List<Choice> choices = choiceBean.getChoiceList();
            response = Response.ok(choices).build();
        /*
        }
        */
        return response;
    }
    
    @GET
    @Operation(description = GET_CHOICE_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CHOICE_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CHOICE_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CHOICE_BY_ID_OP_404_DESC)
    })
    @RolesAllowed(USER_ROLENAME)
    @Path(CHOICE_RESOURCE_PATH_ID_PATH)
    public Response getEmployeeById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
        @PathParam(CHOICE_RESOURCE_PATH_ID_ELEMENT) int id) {
        Response response = null;

        /*
        if (!sc.isCallerInRole(USER_ROLENAME)) {
            //TODO - check if specific user is allowed to retrieve the specific employee
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_DESC_JSON_MSG).build();
        }
        else {
        */
        Choice choice = choiceBean.getChoiceById(id);
            if (choice == null) {
                response = Response.status(NOT_FOUND).build();
            }
            else {
                response = Response.ok(choice).build();
            }
        /*
        }
        */

        return response;
    }
    
    @POST
    @Path("/create/{param}")//
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@PathParam("param") Product product) {
        boolean output = choiceBean.addChoice(product);      
        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createQuery(@QueryParam("param") Product product) {
        boolean output = choiceBean.addChoice(product);
        return Response.status(200).entity(output).build();
    }
    
    @PUT
    @Path("/update/{id}")//
    @Produces("application/json")
    public Response updateChoice(@PathParam("id")int id)
    { 
        Choice choice = choiceBean.getChoiceById(id);
        Choice updated = choiceBean.updateChoice2(choice);
        return Response.ok(updated).build();
    }
  
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id")int id){
       Choice choice = choiceBean.getChoiceById(id);
       boolean output = choiceBean.deleteChoice(choice);
    return Response.status(200).entity(output).build();
    }
    

}
