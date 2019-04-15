/********************************************************************egg***m******a**************n************
 * File: ChoiceResource.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.rest.ChoiceConstants.ADD_CHOICE_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.ADD_CHOICE_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.ADD_CHOICE_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.ADD_CHOICE_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.UPDATE_CHOICE_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.UPDATE_CHOICE_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.UPDATE_CHOICE_OP_404_DESC; 
import static com.algonquincollege.cst8277.rest.ChoiceConstants.UPDATE_CHOICE_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.CHOICE_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ChoiceConstants.GET_CHOICE_OP_404_DESC;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.CartBean;
import com.algonquincollege.cst8277.ejb.ChoiceBean;
import com.algonquincollege.cst8277.ejb.ProductBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Choice;
import com.algonquincollege.cst8277.models.Product;

/**
 * Resource class for Choice entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(CHOICE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChoiceResource { 

    /**
     * dependency on ChoiceBean ejb
     */
    @EJB
    protected ChoiceBean choiceBean;

    /**
     * dependency on ProductBean ejb
     */
    @EJB
    protected ProductBean productBean;
    
    /**
     * dependency on CartBean ejb
     */
    @EJB
    protected CartBean cartBean;
    
    /**
     * injected SecurityContext
     */
    @Inject
    protected SecurityContext sc;

    /**
     * finds list of Choice
     * @return Response response
     */
    @GET
    @Operation(description = GET_CHOICE_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CHOICE_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CHOICE_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CHOICE_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChoices() {
        Response response = null;
        List<Choice> choices = choiceBean.getChoiceList();
        response = Response.ok(choices).build();
        return response;
    }
    
    /**
     * adds new Choice
     * @param quantity
     * @param cartId
     * @param prodId
     * @return Response response
     */
    @POST
    @Path("/{quantity}/{cartId}/{prodtId}")
    @Operation(description = ADD_CHOICE_OP_DESC)
    @APIResponses({      
        @APIResponse(responseCode = "200", description = ADD_CHOICE_OP_200_DESC),
        @APIResponse(responseCode = "403", description = ADD_CHOICE_OP_403_DESC),
        @APIResponse(responseCode = "404", description = ADD_CHOICE_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME) 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addChoice(
            @PathParam("quantity") int quantity,
            @PathParam("cartId") int cartId,
            @PathParam("prodtId") int prodId) { 
        Response response = null;
        Choice newChoice = new Choice();
        newChoice.setQuantity(quantity);
        Cart cart = cartBean.getCartById(cartId);
        Product prod = productBean.getProductById(prodId);
        newChoice.setCart(cart);
        newChoice.setProduct(prod);
        int id = choiceBean.addChoice(newChoice);
        response = Response.ok(id).build();
        return response;
    }
    
    /**
     * updates a choice
     * @param choiceId
     * @param quantity
     * @param cartId
     * @param prodId
     * @return Response response
     */
    @PUT
    @Operation(description = UPDATE_CHOICE_OP_DESC)
    @Path("/{choiceId}/{quantity}/{cartId}/{prodId}")
    @APIResponses({
        @APIResponse(responseCode = "200", description = UPDATE_CHOICE_OP_200_DESC),
        @APIResponse(responseCode = "403", description = UPDATE_CHOICE_OP_403_DESC),
        @APIResponse(responseCode = "404", description = UPDATE_CHOICE_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateChoice(
            @PathParam("choiceId") int choiceId, 
            @PathParam("quantity") int quantity,
            @PathParam("cartId") int cartId,
            @PathParam("prodId") int prodId) {
        Response response = null;
        Choice updatedChoice = choiceBean.getChoiceById(choiceId);  
        updatedChoice.setQuantity(quantity);    
        Cart cart = cartBean.getCartById(cartId);
        Product pr = productBean.getProductById(prodId);
        updatedChoice.setCart(cart);
        updatedChoice.setProduct(pr);
        
        choiceBean.updateChoice(updatedChoice);
        response = Response.ok().build();
        return response;
    }
    
    /**
     * finds choice by id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_CHOICE_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CHOICE_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CHOICE_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CHOICE_BY_ID_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    @Path("/{cartId}/{productId}")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getChoicesByCartAndProductId(@PathParam("cartId") int cartId,
            @PathParam("productId") int productId) {
        Response response = null;

        List<Choice> choices = choiceBean.getChoicesByCartAndProductId(cartId, productId);
        response = Response.ok(choices).build();
        return response;
    }

  
    /**
     * deletes choice 
     * @param id
     * @return Response response
     */
    @DELETE
    @RolesAllowed(USER_ROLENAME)
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id){
       Choice choice = choiceBean.getChoiceById(id);
       boolean output = choiceBean.deleteChoice(choice);
       return Response.status(200).entity(output).build();
    }

}
