package com.algonquincollege.cst8277.rest;

public interface ProductConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String PRODUCT_NAME = "product name";
    public static final String PRODUCT_ID = "product id";
    public static final String PRODUCT_PRICE = "product price";
    //TODO go through and define or modify constants
    public static final String PRODUCT_RESOURCE_NAME = "product";
    public static final String PRODUCT_RESOURCE_PATH_ID_ELEMENT = "id";
    public static final String PRODUCT_RESOURCE_PATH_NAME_ELEMENT = "name";
    public static final String PRODUCT_RESOURCE_PATH_PRICE_ELEMENT = "price";
    public static final String PRODUCT_CATEGORY_RESOURCE_PATH_ID_ELEMENT = "categoryId";
    public static final String PRODUCT_RESOURCE_PATH_ID_PATH =  "{" + PRODUCT_RESOURCE_PATH_ID_ELEMENT + "}";
    public static final String PRODUCT_RESOURCE_PATH_NAME_PATH =  "/{" + PRODUCT_RESOURCE_PATH_NAME_ELEMENT + "}";
    public static final String PRODUCT_RESOURCE_PATH_PRICE_PATH =  "/{" + PRODUCT_RESOURCE_PATH_PRICE_ELEMENT + "}";

    public static final String GET_PRODUCT_OP_DESC = "Retrieves list of Products";
    public static final String GET_PRODUCT_OP_200_DESC = "Successful, returning products";
    public static final String GET_PRODUCT_OP_403_DESC = "Only admin's can list all products";
    public static final String GET_PRODUCT_OP_404_DESC = "Could not find product";
    public static final String GET_PRODUCT_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_PRODUCT_OP_403_DESC + SUFFIX_JSON_MSG;

    //TODO - update descriptions
    public static final String UPDATE_PRODUCT_OP_DESC = "Update list of Products";
    public static final String UPDATE_PRODUCT_OP_200_DESC = "Successful, updating product";
    public static final String UPDATE_PRODUCT_OP_403_DESC = "Only admin's can update product";
    public static final String UPDATE_PRODUCT_OP_404_DESC = "Could not find product";
    public static final String UPDATE_PRODUCT_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + UPDATE_PRODUCT_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_PRODUCT_BY_ID_OP_DESC = "Retrieve specific product";
    public static final String GET_PRODUCT_BY_CATEGORY_ID_OP_DESC = "Retrieve products related to a specific category";
    public static final String GET_PRODUCT_BY_ID_OP_200_DESC = "Successful, returning requested product";
    public static final String GET_PRODUCT_BY_ID_OP_403_DESC = "Only user's can retrieve a specific product";
    public static final String GET_PRODUCT_BY_ID_OP_404_DESC = "Requested product is not found";
    public static final String GET_PRODUCT_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_PRODUCT_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    public static final String ADD_PRODUCT_OP_DESC = "Add to list of Products";
    public static final String ADD_PRODUCT_OP_200_DESC = "Successful, adding products";
    public static final String ADD_PRODUCT_OP_403_DESC = "Only admin's can add products";
    public static final String ADD_PRODUCT_OP_404_DESC = "Could not add product";
}