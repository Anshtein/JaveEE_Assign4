/********************************************************************egg***m******a**************n************
 * File: CategoryResource.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */

package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_PATH_ID_PATH;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_PATH_NAME_ELEMENT;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_OP_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.UPDATE_CATEGORY_OP_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.GET_CATEGORY_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.ADD_CATEGORY_OP_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.ADD_CATEGORY_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.ADD_CATEGORY_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.ADD_CATEGORY_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_NAME;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_ID;
import static com.algonquincollege.cst8277.rest.CategoryConstants.DELETE_CATEGORY_BY_ID;
import static com.algonquincollege.cst8277.rest.CategoryConstants.DELETE_CATEGORY_BY_ID_OP_200;
import static com.algonquincollege.cst8277.rest.CategoryConstants.DELETE_CATEGORY_BY_ID_OP_403;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
//import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
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
//import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.ProductCategoryBean;
import com.algonquincollege.cst8277.models.Category;
import com.algonquincollege.cst8277.models.Product;


/**
 * Resource class for Category entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(CATEGORY_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    /**
     * dependency on ProductCategoryBean ejb
     */
    @EJB
    protected ProductCategoryBean prodCategoryBean;

    /**
     * injected SecurityContext
     */
    @Inject
    protected SecurityContext sc;

    /**
     * finds all categories
     * @return Response response
     */
    @GET
    @Operation(description = GET_CATEGORY_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CATEGORY_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CATEGORY_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CATEGORY_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getCategories() {
        Response response = null;

        List<Category> categories = prodCategoryBean.getCategoryList();
        response = Response.ok(categories).build();

        return response;
    }

    /**
     * adds new category
     * @param categoryName
     * @return Response response
     */
    @POST
    @Operation(description = ADD_CATEGORY_OP_DESC)
    @APIResponses({

        @APIResponse(responseCode = "200", description = ADD_CATEGORY_OP_200_DESC),
        @APIResponse(responseCode = "403", description = ADD_CATEGORY_OP_403_DESC),
        @APIResponse(responseCode = "404", description = ADD_CATEGORY_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response addCategories(@Parameter(description = CATEGORY_NAME, required = true)
    @QueryParam(CATEGORY_RESOURCE_PATH_NAME_ELEMENT) String categoryName) 
    {
        Response response = null;
        Category catWithAddedFields = new Category();
        catWithAddedFields.setName(categoryName);
        int id = prodCategoryBean.addCategory(catWithAddedFields);
        response = Response.ok(id).build();

        return response;
    }

    /**
     * updates category
     * @param id
     * @param categoryName
     * @return Response response
     */
    @PUT
    @Operation(description = UPDATE_CATEGORY_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = UPDATE_CATEGORY_OP_200_DESC),
        @APIResponse(responseCode = "403", description = UPDATE_CATEGORY_OP_403_DESC),
        @APIResponse(responseCode = "404", description = UPDATE_CATEGORY_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response updateCategory(@Parameter(description = CATEGORY_ID, required = true)
    @QueryParam(CATEGORY_RESOURCE_PATH_ID_ELEMENT) String id,
    @Parameter(description = CATEGORY_NAME, required = true)
    @QueryParam(CATEGORY_RESOURCE_PATH_NAME_ELEMENT) String categoryName) {
        Response response = null;
        Category catWithUpdatedFields = prodCategoryBean.getCategoryById(Integer.parseInt(id));
        catWithUpdatedFields.setId(Integer.parseInt(id));
        catWithUpdatedFields.setName(categoryName);
        prodCategoryBean.updateCategory(catWithUpdatedFields);
        response = Response.ok().build();

        return response;
    }

    /**
     * get a category by id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_CATEGORY_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_CATEGORY_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_CATEGORY_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_CATEGORY_BY_ID_OP_404_DESC)
    })

    @RolesAllowed(ADMIN_ROLENAME)
    @Path(CATEGORY_RESOURCE_PATH_ID_PATH)
    public Response getCategoryById(@PathParam("id") String id) {
        Response response = null;

        Category category = prodCategoryBean.getCategoryById(Integer.parseInt(id));
        if (category == null) {
            response = Response.status(NOT_FOUND).build();
        }
        else {
            response = Response.ok(category).build();
        }
        return response;
    }

    /**
     * deletes a category
     * @param id
     * @return Response response
     */
    @DELETE
    @Operation(description = DELETE_CATEGORY_BY_ID)
    @APIResponses({
        @APIResponse(responseCode = "200", description = DELETE_CATEGORY_BY_ID_OP_200),
        @APIResponse(responseCode = "403", description = DELETE_CATEGORY_BY_ID_OP_403),
        @APIResponse(responseCode = "404", description = GET_CATEGORY_BY_ID_OP_404_DESC)
    })
    
    @RolesAllowed(ADMIN_ROLENAME)
    public Response deleteCategoryById(@Parameter(description = CATEGORY_ID, required = true)
                                      @QueryParam(CATEGORY_RESOURCE_PATH_ID_ELEMENT) String id) {
        Response response = null;

        Category category = prodCategoryBean.getCategoryById(Integer.parseInt(id));
        if (category == null) {
            response = Response.status(NOT_FOUND).build();
        }
        else {
            prodCategoryBean.deleteCategoryById(Integer.parseInt(id));
            response = Response.ok(category).build();
        }
        return response;
    }

}