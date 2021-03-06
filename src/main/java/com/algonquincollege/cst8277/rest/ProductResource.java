/********************************************************************egg***m******a**************n************
 * File: ProductResource.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.rest;


import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_RESOURCE_PATH_NAME_ELEMENT;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_RESOURCE_PATH_PRICE_ELEMENT;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_CATEGORY_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_OP_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.UPDATE_PRODUCT_OP_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.ADD_PRODUCT_OP_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.ADD_PRODUCT_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.ADD_PRODUCT_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.ADD_PRODUCT_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.GET_PRODUCT_BY_CATEGORY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_NAME;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_PRICE;
import static com.algonquincollege.cst8277.rest.ProductConstants.PRODUCT_ID;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_ID;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.CategoryConstants.CATEGORY_EXTERNAL_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.ProductConstants.DELETE_PRODUCT_BY_ID;
import static com.algonquincollege.cst8277.rest.ProductConstants.DELETE_PRODUCT_BY_ID_OP_200;
import static com.algonquincollege.cst8277.rest.ProductConstants.DELETE_PRODUCT_BY_ID_OP_403;
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
//import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.ProductBean;
import com.algonquincollege.cst8277.ejb.ProductCategoryBean;
import com.algonquincollege.cst8277.models.Category;
import com.algonquincollege.cst8277.models.Product; 

/**
 * Resource class for Product entity
 * annotated with Path, accepted and produced media type (json format)
 * 
 * method annotations describing:
 * response to HTTP request
 * describes a sinble API operation on a path
 * error messages in case of network or other problems
 * security role permitted to access this method
 */
@Path(PRODUCT_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    /**
     * dependency on ProductBean ejb
     */
    @EJB
    protected ProductBean prodBean;

    /**
     * dependency on ProductCategoryBean ejb
     */
    @EJB
    protected ProductCategoryBean categoryBean;

    /**
     * injected SecurityContext
     */
    @Inject
    protected SecurityContext sc;

    /**
     * finds all products
     * @return Response response
     */
    @GET
    @Operation(description = GET_PRODUCT_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_PRODUCT_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getProducts() {
        Response response = null;

        List<Product> categories = prodBean.getProductList();
        response = Response.ok(categories).build();

        return response;
    }

    /**
     * adds a new product
     * @param prodName
     * @param prodPrice
     * @param categoryId
     * @return Response response
     */
    @POST
    @Operation(description = ADD_PRODUCT_OP_DESC)
    @APIResponses({

        @APIResponse(responseCode = "200", description = ADD_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description = ADD_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description = ADD_PRODUCT_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response addProduct( @Parameter(description = PRODUCT_NAME, required = true)
    @QueryParam(PRODUCT_RESOURCE_PATH_NAME_ELEMENT) String prodName,
    @Parameter(description = PRODUCT_PRICE, required = false)
    @QueryParam(PRODUCT_RESOURCE_PATH_PRICE_ELEMENT) String prodPrice,
    @Parameter(description = CATEGORY_ID, required = false)
    @QueryParam(PRODUCT_CATEGORY_RESOURCE_PATH_ID_ELEMENT) String categoryId) {
        Response response = null;
        Product prodWithAddedFields = new Product();
        prodWithAddedFields.setName(prodName);

        if(prodPrice != null && !prodPrice.isEmpty())
            prodWithAddedFields.setPrice(Double.parseDouble(prodPrice));

        if(categoryId != null && !categoryId.isEmpty()) {
            Category cat = categoryBean.getCategoryById(Integer.parseInt(categoryId));

            if(cat == null || cat.getId() == 0) {
                return Response.status(NOT_FOUND).build();
            }

            prodWithAddedFields.setCategories(Arrays.asList(cat));
        }

        int id = prodBean.addProduct(prodWithAddedFields);
        response = Response.ok(id).build();

        return response;
    }

    /**
     * updates a product
     * @param id
     * @param prodName
     * @param prodPrice
     * @param categoryId
     * @return Response response
     */
    @PUT
    @Operation(description = UPDATE_PRODUCT_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = UPDATE_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description = UPDATE_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description = UPDATE_PRODUCT_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response updateProduct(@Parameter(description = PRODUCT_ID, required = true)
    @QueryParam(PRODUCT_RESOURCE_PATH_ID_ELEMENT) String id,
    @Parameter(description = PRODUCT_NAME, required = false)
    @QueryParam(PRODUCT_RESOURCE_PATH_NAME_ELEMENT) String prodName,
    @Parameter(description = PRODUCT_PRICE, required = false)
    @QueryParam(PRODUCT_RESOURCE_PATH_PRICE_ELEMENT) String prodPrice,
    @Parameter(description = CATEGORY_ID, required = false)
    @QueryParam(PRODUCT_CATEGORY_RESOURCE_PATH_ID_ELEMENT) String categoryId) {
        Response response = null;
        Product prodWithUpdatedFields = prodBean.getProductById(Integer.parseInt(id));

        if(prodName != null && !prodName.isEmpty())
            prodWithUpdatedFields.setName(prodName);

        if(prodPrice != null && !prodPrice.isEmpty())
            prodWithUpdatedFields.setPrice(Double.parseDouble(prodPrice));

        if(categoryId != null && !categoryId.isEmpty()) {
            Category cat = categoryBean.getCategoryById(Integer.parseInt(categoryId));

            //get categories
            List<Category> cats = prodWithUpdatedFields.getCategories();

            if(cats != null && cats.size() > 0) {
                // add to existing categories
                cats.add(cat);
                // set categories
                prodWithUpdatedFields.setCategories(cats);
            }
            else {
                prodWithUpdatedFields.setCategories(Arrays.asList(cat));
            }
        }

        prodBean.updateProduct(prodWithUpdatedFields);
        response = Response.ok().build();

        return response;
    }


    /**
     * finds product by id
     * @param id
     * @return Response response
     */
    @GET
    @Operation(description = GET_PRODUCT_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_PRODUCT_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_PRODUCT_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_PRODUCT_BY_ID_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    @Path("{id}")
    public Response getProductById(@PathParam("id") String id) {
        Response response = null;

        Product prod = prodBean.getProductById(Integer.parseInt(id));
        if (prod == null) {
            response = Response.status(NOT_FOUND).build();
        }
        else {
            response = Response.ok(prod).build();
        }

        return response;
    }

    /**
     * finds product by category id
     * @param id
     * @return Response response
     */
    @GET
    @Path(CATEGORY_RESOURCE_NAME)
    @Operation(description = GET_PRODUCT_BY_CATEGORY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_PRODUCT_OP_404_DESC)
    })
    @RolesAllowed({USER_ROLENAME, ADMIN_ROLENAME})
    public Response getProductByCategoryId(@Parameter(description = PRIMARY_KEY_DESC, required = true)
    @QueryParam(CATEGORY_EXTERNAL_RESOURCE_PATH_ID_ELEMENT) String id) {
        Response response = null;

        if(id == null || id.isEmpty())
            return Response.status(NOT_FOUND).build();

        List<Product> prods = prodBean.getProductByCategoryId(Integer.parseInt(id));

        response = Response.ok(prods).build();

        return response;
    }
    
    @DELETE
    @Operation(description = DELETE_PRODUCT_BY_ID)
    @APIResponses({
        @APIResponse(responseCode = "200", description = DELETE_PRODUCT_BY_ID_OP_200),
        @APIResponse(responseCode = "403", description = DELETE_PRODUCT_BY_ID_OP_403),
        @APIResponse(responseCode = "404", description = GET_PRODUCT_BY_ID_OP_404_DESC)
    })
    
    @RolesAllowed(ADMIN_ROLENAME)
    public Response deleteProductById(@Parameter(description = PRODUCT_ID, required = true)
                                      @QueryParam(PRODUCT_RESOURCE_PATH_ID_ELEMENT) String id) {
        Response response = null;

        Product product = prodBean.getProductById(Integer.parseInt(id));
        if (product == null) {
            response = Response.status(NOT_FOUND).build();
        }
        else {
            prodBean.deleteProductById(Integer.parseInt(id));
            response = Response.ok(product).build();
        }
        return response;
    }

    

}