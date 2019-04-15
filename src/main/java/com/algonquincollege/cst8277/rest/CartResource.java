/********************************************************************egg***m******a**************n************
 * File: CartResource.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 *
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.rest.CartConstants.CART_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.CART_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.CartConstants.CART_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.PRIMARY_KEY_DESC;

import static com.algonquincollege.cst8277.rest.CartConstants.CART_ID;
import static com.algonquincollege.cst8277.rest.CartConstants.UPDATE_CART_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.UPDATE_CART_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.UPDATE_CART_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.UPDATE_CART_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.ContactConstants.CUSTOMER_EXTERNAL_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_DESC;

import static com.algonquincollege.cst8277.rest.CartConstants.OWNING_CUST_ID;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.ADD_CART_OP_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.ADD_CART_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.ADD_CART_OP_403_DESC; 
import static com.algonquincollege.cst8277.rest.CartConstants.ADD_CART_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.CART_RESOURCE_PATH_CUST_ID_ELEMENT;


import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.Arrays;
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

import com.algonquincollege.cst8277.ejb.CartBean;
import com.algonquincollege.cst8277.ejb.ChoiceBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Contact;

/**
 * Resource class for Cart entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(CART_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    /**
     * dependency on CartBean ejb
     */
    @EJB
    protected CartBean cartBean;
    /**
     * dependency on ChoiceBean ejb
     */
    @EJB
    protected ChoiceBean choiceBean;

    /**
     * injected SecurityContext
     */
    @Inject
    protected SecurityContext sc;

    /**
     * finds all carts
     * @return Response response
     */
    @GET
    @Operation(description = GET_CART_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CART_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CART_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CART_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getCarts() {
        Response response = null;
        List<Cart> carts = cartBean.getCartList();
        response = Response.ok(carts).build();
        return response;
    }
    /**
     * adds a new cart
     * @param custId
     * @return Response response
     */
    @POST
    @Operation(description = ADD_CART_OP_DESC)
    @APIResponses({      
        @APIResponse(responseCode = "200", description = ADD_CART_OP_200_DESC),
        @APIResponse(responseCode = "403", description = ADD_CART_OP_403_DESC),
        @APIResponse(responseCode = "404", description = ADD_CART_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME) ///USER ROLE
    public Response addCart(
            @Parameter(description = OWNING_CUST_ID, required = true)
            @QueryParam(CART_RESOURCE_PATH_CUST_ID_ELEMENT) int custId) { 
        Response response = null;
        Cart newCart = new Cart();
        newCart.getCustomer().setId(custId);;

        int id = cartBean.addCart(newCart);
        response = Response.ok(id).build();
        return response;
    }
    /**
     * updates a cart
     * @param id
     * @param custId
     * @return Response response
     */
    @PUT
    @Operation(description = UPDATE_CART_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = UPDATE_CART_OP_200_DESC),
        @APIResponse(responseCode = "403", description = UPDATE_CART_OP_403_DESC),
        @APIResponse(responseCode = "404", description = UPDATE_CART_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response updateCart(
            @Parameter(description = CART_ID, required = true)
            @QueryParam(CART_RESOURCE_PATH_ID_ELEMENT) int id,
            @Parameter(description = OWNING_CUST_ID, required = true)
            @QueryParam(CART_RESOURCE_PATH_CUST_ID_ELEMENT) int custId) {
        Response response = null;
        Cart updatedCart = cartBean.getCartById(id);  
        updatedCart.getCustomer().setId(custId);
        cartBean.updateCart(updatedCart);
        response = Response.ok().build();
        return response;
    }
    
    /**
     * finds cart by cart id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_CART_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CART_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CART_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CART_BY_ID_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    @Path("{id}")
    public Response getCartById(@PathParam("id") String id) {
        Response response = null;
        Cart cart = cartBean.getCartById(Integer.parseInt(id));
        if (cart == null) {
            response = Response.status(NOT_FOUND).build();
        }
        else {
            response = Response.ok(cart).build();
        }
        return response;
    }
    /**
     * finds Cart by customer id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_CART_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CART_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CART_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CART_BY_ID_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    @Path(CART_RESOURCE_PATH_ID_PATH)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getCartByCustomerId(
            @Parameter(description = PRIMARY_KEY_DESC, required = true)
            @QueryParam(CART_RESOURCE_PATH_CUST_ID_ELEMENT) String id) {
        Response response = null;
        if(id == null || id.isEmpty())
            return Response.status(NOT_FOUND).build();
        
        Cart cart = cartBean.getCartByCustomerId(Integer.parseInt(id));
        response = Response.ok(cart).build();
        return response;
    }
    
    
}
