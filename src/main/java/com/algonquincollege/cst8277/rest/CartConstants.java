package com.algonquincollege.cst8277.rest;

public interface CartConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String CART_ID = "cart id";
    public static final String CART_RESOURCE_NAME =  "cart";
    public static final String CART_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String CART_RESOURCE_PATH_ID_PATH =  "/{" + CART_RESOURCE_PATH_ID_ELEMENT + "}";
 
    
    public static final String GET_CART_OP_DESC = "Retrieves list of carts";
    public static final String GET_CART_OP_200_DESC = "Successful, returning carts";
    public static final String GET_CART_OP_403_DESC = "Only admin's can list all carts";
    public static final String GET_CART_OP_404_DESC = "Could not find carts";
    public static final String GET_CART_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_CART_OP_403_DESC + SUFFIX_JSON_MSG;
    
    public static final String GET_CART_BY_ID_OP_DESC = "Retrieve specific cart";
    public static final String GET_CART_BY_ID_OP_200_DESC = "Successful, returning requested cart";
    public static final String GET_CART_BY_ID_OP_403_DESC = "Only user's can retrieve a specific cart";
    //? Only user's can retrieve a specific cart
    public static final String GET_CART_BY_ID_OP_404_DESC = "Requested cart not found";
    public static final String GET_CART_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_CART_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    
    public static final String ADD_CART_OP_DESC = "Add to list of Carts";
    public static final String ADD_CART_OP_200_DESC = "Successful, adding cart";
    public static final String ADD_CART_OP_403_DESC = "Only admin's can add carts";
    public static final String ADD_CART_OP_404_DESC = "Could not add cart";
    
    public static final String OWNING_CUST_ID = "customer id";
    public static final String CART_RESOURCE_PATH_CUST_ID_PATH =  "/{" + OWNING_CUST_ID + "}";
    public static final String CART_RESOURCE_PATH_CUST_ID_ELEMENT = "customerId";
    
    public static final String UPDATE_CART_OP_DESC = "Update list of carts";
    public static final String UPDATE_CART_OP_200_DESC = "Successful, updating cart";
    public static final String UPDATE_CART_OP_403_DESC = "Only admin's can update cart";
    public static final String UPDATE_CART_OP_404_DESC = "Could not find cart";
    
}
