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
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_OP_404_DESC;
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

import com.algonquincollege.cst8277.ejb.CartBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Customer;


@Path(CART_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    
    @EJB
    protected CartBean cartBean;
    
    @Inject
    protected SecurityContext sc;
    
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

        /*
        if (!sc.isCallerInRole(ADMIN_ROLENAME)) {
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_JSON_MSG).build();
        }
        else {
        */
            List<Cart> carts = cartBean.getCartList();
            response = Response.ok(carts).build();
        /*
        }
        */
        return response;
    }
    
    @GET
    @Operation(description = GET_CART_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CART_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CART_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CART_BY_ID_OP_404_DESC)
    })
    @RolesAllowed(USER_ROLENAME) //только user может это, не admin
    @Path(CART_RESOURCE_PATH_ID_PATH)
    public Response getCartById(@Parameter(description = PRIMARY_KEY_DESC, required = true) 
        @PathParam(CART_RESOURCE_PATH_ID_ELEMENT) int id) {
        Response response = null;

        /*
        if (!sc.isCallerInRole(USER_ROLENAME)) {
        
            //TODO - check if specific user is allowed to retrieve the specific cart!!!!!!!!!!!!!!!!!
             * 
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_DESC_JSON_MSG).build();
        }
        else {
        */
        Cart cart = cartBean.getCartById(id);
            if (cart == null) {
                response = Response.status(NOT_FOUND).build();
            }
            else {
                response = Response.ok(cart).build();
            }
        /*
        }
        */

        return response;
    }
    
    
    @POST
    @Path("/create/{param}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@PathParam("param") Customer customer) {
        boolean output = cartBean.addCart(customer);      
        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createQuery(@QueryParam("param") Customer customer) {
        boolean output = cartBean.addCart(customer);
        return Response.status(200).entity(output).build();
    }
    
    @PUT
    @Path("/update/{id}") 
    @Produces("application/json")
    public Response updateCart(@PathParam("id")int id)
    {
        Cart cart = cartBean.getCartById(id);
        Cart updated = cartBean.updateCart2(cart);
        return Response.ok(updated).build();
    }
  
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id")int id){
       Cart cart = cartBean.getCartById(id);
       boolean output = cartBean.deleteCart(cart);
    return Response.status(200).entity(output).build();
    }
}
