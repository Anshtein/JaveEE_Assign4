/********************************************************************egg***m******a**************n************
 * File: CustomerResource.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.rest;


import static com.algonquincollege.cst8277.rest.CustomerConstants.CUSTOMER_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.CustomerConstants.EMPLOYEE_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.CustomerConstants.EMPLOYEE_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEES_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEES_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEES_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_CUSTOMERS_OP_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.GET_EMPLOYEE_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.CustomerConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.SimpleBean;
import com.algonquincollege.cst8277.models.Customer;
import com.sun.mail.imap.protocol.Status;

/**
 * Resource class for Customer entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(CUSTOMER_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

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
     * finds all customers
     * @return Response response
     */
    @GET
    @Operation(description = GET_CUSTOMERS_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_EMPLOYEES_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_EMPLOYEES_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_EMPLOYEES_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getCustomers() {
        Response response = null;

        /*
        if (!sc.isCallerInRole(ADMIN_ROLENAME)) {
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_JSON_MSG).build();
        }
        else {
        */
            List<Customer> emps = simpleBean.getCustomerList();
//            List<String> names = new ArrayList<>();
//            for (Customer customer : emps) {
//            	names.add(customer.toString());				
//			}
            response = Response.ok(emps).build();

        return response;
    }

    /**
     * finds customer by is
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_EMPLOYEE_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_EMPLOYEE_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_EMPLOYEE_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_EMPLOYEE_BY_ID_OP_404_DESC)
    })
    @RolesAllowed(USER_ROLENAME)
    @Path(EMPLOYEE_RESOURCE_PATH_ID_PATH)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getCustomerById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
        @PathParam(EMPLOYEE_RESOURCE_PATH_ID_ELEMENT) int id) {
        Response response = null;
        Principal user = sc.getCallerPrincipal();
        
        
        
        if (!simpleBean.checkCustomerUsernameId(user.getName(), id)) {

            response = Response.status(javax.ws.rs.core.Response.Status.FORBIDDEN).build();
        }
        else if (simpleBean.checkCustomerUsernameId(user.getName(), id)){
        Customer emp = simpleBean.getCustomerById(id);
        emp.getUser().setPwHash("*************");
        String result = emp.toString();
            if (emp == null) {
                response = Response.status(NOT_FOUND).build();
            }
            else {
                response = Response.ok(emp).build();
            }
        }

        return response;
    }
    
    /**
     * creates new customer
     * @param param1
     * @param param2
     * @return Response response
     */
    @POST
    @Path("/{param1}/{param2}")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@PathParam("param1") String param1,
                       @PathParam("param2") String param2) {
        boolean output = simpleBean.addCustomer(param1, param2);      
        return Response.status(200).entity(output).build();
    }
    
    /**
     * creates a query
     * @param param1
     * @param param2
     * @return Response response
     */
    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createQuery(@QueryParam("param1") String param1,
                       @QueryParam("param2") String param2) {
        boolean output = simpleBean.addCustomer(param1, param2);
        return Response.status(200).entity(output).build();
    }
    
    /**
     * updates a customer with new parameters
     * @param id
     * @param firstName
     * @param lastName
     * @return Response response
     */
    @PUT
    @RolesAllowed(USER_ROLENAME)
    @Path("/{id}/{firstName}/{lastName}")
    @Produces("application/json")
    public Response updateCustomer(@PathParam("id")int id, @PathParam("firstName") String firstName, @PathParam("lastName") String lastName)
    {
    	Customer customer = simpleBean.getCustomerById(id);
    	if(!firstName.isEmpty()) customer.setFirstName(firstName);
    	if(!lastName.isEmpty()) customer.setLastName(lastName);
    	Customer updated = simpleBean.updateCustomer(customer);
    	return Response.ok(updated).build();
    }
  
    
    /**
     * deletes a customer
     * @param id
     * @return Response response
     */
    @DELETE
    @RolesAllowed(USER_ROLENAME)
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id")int id){
       Customer customer = simpleBean.getCustomerById(id);
       boolean output = simpleBean.deleteCustomer(customer);
	return Response.status(200).entity(output).build();
    }
    

}