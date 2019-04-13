package com.algonquincollege.cst8277.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import com.algonquincollege.cst8277.ejb.ContactBean;
import com.algonquincollege.cst8277.models.Contact;
import com.algonquincollege.cst8277.models.Customer;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ContactConstants.CONTACT_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_403_DESC_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.GET_CONTACTS_OP_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.PREFIX_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ContactConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.ContactConstants.SUFFIX_JSON_MSG;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;

import java.util.List;


@Path(CONTACT_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    
    @EJB
    protected ContactBean contactBean;
    
    @Inject
    protected SecurityContext sc;
    
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
}
