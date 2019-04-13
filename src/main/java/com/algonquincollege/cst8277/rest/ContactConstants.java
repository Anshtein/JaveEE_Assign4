package com.algonquincollege.cst8277.rest;

public interface ContactConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";
    
    public static final String CONTACT_RESOURCE_NAME =  "contact";
    public static final String CONTACT_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String CUSTOMER_EXTERNAL_RESOURCE_PATH_ID_ELEMENT = "categoryId";
    public static final String CONTACT_RESOURCE_PATH_ID_PATH =  "/{" + CONTACT_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_CONTACTS_OP_DESC = "Retrieves list of contacts";
    public static final String GET_CONTACTS_OP_200_DESC = "Successful, returning contacts";
    public static final String GET_CONTACTS_OP_403_DESC = "Only admin's can list all contacts";
    public static final String GET_CONTACTS_OP_404_DESC = "Could not find contacts";
    public static final String GET_CONTACTS_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_CONTACTS_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_CONTACT_BY_ID_OP_DESC = "Retrieve specific contact";
    public static final String GET_CONTACT_BY_ID_OP_200_DESC = "Successful, returning requested contact";
    public static final String GET_CONTACT_BY_ID_OP_403_DESC = "Only user's can retrieve a specific contact";
    public static final String GET_CONTACT_BY_ID_OP_404_DESC = "Requested contact not found";
    public static final String GET_CONTACT_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_CONTACT_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
  
}