/********************************************************************egg***m******a**************n************
 * File: ContactResource.java
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

import javax.annotation.security.PermitAll;
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

import com.algonquincollege.cst8277.ejb.ContactBean;
import com.algonquincollege.cst8277.ejb.SimpleBean;
import com.algonquincollege.cst8277.models.Contact;
import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.Product;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_EXTERNAL_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACT_OP_403_DESC_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.PREFIX_JSON_MSG;
import static com.algonquincollege.cst8277.rest.CustomerConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.SUFFIX_JSON_MSG;
import static com.algonquincollege.cst8277.rest.CustomerConstants.EMPLOYEE_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.CustomerConstants.EMPLOYEE_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.CUSTOMER_EXTERNAL_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.security.Principal;
import java.util.List;

/**
 * Resource class for Contact entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(CONTACT_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    
    /**
     * dependency on ContactBean ejb
     */
    @EJB
    protected ContactBean contactBean;
    
    /**
     * dependency on SimpleBean ejb
     */
    @EJB
    protected SimpleBean simpleBean;
    
    /**
     * injected SecurityContext
     */
    @Inject
    protected SecurityContext sc;
    
    /**
     * finds all contacts
     * @return List of contacts
     */
    @GET
    @Operation(description = GET_CONTACTS_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CONTACTS_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CONTACTS_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CONTACTS_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getContacts() {
        Response response = null;
        
        List<Contact> contacts = contactBean.getContactsList();
        response = Response.ok(contacts).build();
        
        return response;
    }
    
    /**
     * finds contact by customer id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_CONTACT_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CONTACT_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CONTACT_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CONTACT_BY_ID_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    @Path(CONTACT_RESOURCE_PATH_ID_PATH)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getContactByCustomerId(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                           @QueryParam(CUSTOMER_EXTERNAL_RESOURCE_PATH_ID_ELEMENT) String id) {
        Response response = null;
        
        if(id == null || id.isEmpty())
            return Response.status(NOT_FOUND).build();

        Contact contact = contactBean.getContactByCustomerID(Integer.parseInt(id));
        
        response = Response.ok(contact).build();

        return response;
    }
    
    /**
     * creates contact
     * @param city
     * @param email
     * @param phone
     * @param postalCode
     * @param province
     * @param street
     * @return Response response
     */
    @POST
    @Path("/{city}/{email}/{phone}/{postalCode}/{province}/{street}")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@PathParam("city") String city,
                       @PathParam("email") String email, @PathParam("phone") String phone, 
                       @PathParam("postalCode") String postalCode, @PathParam("province") String province,
                       @PathParam("street") String street) {
        boolean output = contactBean.addContact(city, email, phone, postalCode, province, street);      
        return Response.status(200).entity(output).build();
    }
    
    /**
     * adds new contact to a customer
     * @param customerid
     * @param contactid
     * @return Response response
     */
    @PUT
    @RolesAllowed(USER_ROLENAME)
    @Path("/{customerid}/{contactid}")
    @Produces("application/json")
    public Response addCustomerToContact(@PathParam("customerid")int customerid,
    		@PathParam("contactid") int contactid)
    {	
    	Customer updated = contactBean.updateCustomerToContact(customerid, contactid);
    	return Response.ok(updated).build();
    }
    
    /**
     * updates a contact
     * @param id
     * @param city
     * @param email
     * @param phone
     * @param postalCode
     * @param province
     * @param street
     * @return Response response
     */
    @PUT
    @Path("/{id}/{city}/{email}/{phone}/{postalCode}/{province}/{street}")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateContact(@PathParam("id") String id, @PathParam("city") String city,
                       @PathParam("email") String email, @PathParam("phone") String phone, 
                       @PathParam("postalCode") String postalCode, @PathParam("province") String province,
                       @PathParam("street") String street) {
        Contact output = contactBean.updateContact(id, city, email, phone, postalCode, province, street);      
        return Response.status(200).entity(output).build();
    }
    
    /**
     * deletes contact by customer id
     * @param id
     * @return Response response
     */
    @DELETE
    @RolesAllowed(USER_ROLENAME)
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id")int id){
       boolean output = contactBean.deleteContactByCustID(id);
	return Response.status(200).entity(output).build();
    }
    
    
}
