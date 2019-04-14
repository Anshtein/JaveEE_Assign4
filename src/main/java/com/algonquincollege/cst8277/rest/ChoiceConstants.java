package com.algonquincollege.cst8277.rest;

public interface ChoiceConstants {
    
    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String CHOICE_RESOURCE_NAME =  "choice";
    public static final String CHOICE_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String CHOICE_RESOURCE_PATH_ID_PATH =  "/{" + CHOICE_RESOURCE_PATH_ID_ELEMENT + "}";
    
    public static final String GET_CHOICE_OP_DESC = "Retrieves list of Choices";
    public static final String GET_CHOICE_OP_200_DESC = "Successful, returning employees";
    public static final String GET_CHOICE_OP_403_DESC = "Only admin's can list all employees";
    public static final String GET_CHOICE_OP_404_DESC = "Could not find employees";
    public static final String GET_CHOICE_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_CHOICE_OP_403_DESC + SUFFIX_JSON_MSG;
    
    public static final String GET_CHOICE_BY_ID_OP_DESC = "Retrieve specific choice";
    public static final String GET_CHOICE_BY_ID_OP_200_DESC = "Successful, returning requested choice";
    public static final String GET_CHOICE_BY_ID_OP_403_DESC = "Only user's can retrieve a specific choice";
    //? Only user's can retrieve a specific cart
    public static final String GET_CHOICE_BY_ID_OP_404_DESC = "Requested choice not found";
    public static final String GET_CHOICE_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_CHOICE_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    
    public static final String ADD_CHOICE_OP_DESC = "Add to list of choice";
    public static final String ADD_CHOICE_OP_200_DESC = "Successful, adding choice";
    public static final String ADD_CHOICE_OP_403_DESC = "Only admin's can add choice";
    public static final String ADD_CHOICE_OP_404_DESC = "Could not add choice";
    
    public static final String QUANTITY = "quantity";
    public static final String CHOICE_RESOURCE_PATH_QUANTITY_ELEMENT = "quantity";
    public static final String CHOICE_ID = "choice id";
    
    public static final String UPDATE_CHOICE_OP_DESC = "Update list of choices";
    public static final String UPDATE_CHOICE_OP_200_DESC = "Successful, updating choice";
    public static final String UPDATE_CHOICE_OP_403_DESC = "Only admin's can update choice";
    public static final String UPDATE_CHOICE_OP_404_DESC = "Could not find choice";
    public static final String UPDATE_CHOICE_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + UPDATE_CHOICE_OP_403_DESC + SUFFIX_JSON_MSG;

}
