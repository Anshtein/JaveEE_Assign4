/********************************************************************egg***m******a**************n************
 * File: CategoryConstants.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.rest;

/**
 * interface containing string constants related to Category
 */
public interface CategoryConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String CATEGORY_NAME = "category name";
    public static final String CATEGORY_ID = "category id";
    public static final String CATEGORY_RESOURCE_NAME = "category";
    public static final String CATEGORY_RESOURCE_PATH_ID_ELEMENT = "id";
    public static final String CATEGORY_EXTERNAL_RESOURCE_PATH_ID_ELEMENT = "categoryId";
    public static final String CATEGORY_RESOURCE_PATH_NAME_ELEMENT = "name";
    public static final String CATEGORY_RESOURCE_PATH_ID_PATH =  "{" + CATEGORY_RESOURCE_PATH_ID_ELEMENT + "}";
    public static final String CATEGORY_RESOURCE_PATH_NAME_PATH =  "/{" + CATEGORY_RESOURCE_PATH_NAME_ELEMENT + "}";

    public static final String GET_CATEGORY_OP_DESC = "Retrieves list of Categories";
    public static final String GET_CATEGORY_OP_200_DESC = "Successful, returning categories";
    public static final String GET_CATEGORY_OP_403_DESC = "Only admin's can list all categories";
    public static final String GET_CATEGORY_OP_404_DESC = "Could not find category";
    public static final String GET_CATEGORY_OP_403_JSON_MSG =
            PREFIX_JSON_MSG + GET_CATEGORY_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String UPDATE_CATEGORY_OP_DESC = "Update list of Categories";
    public static final String UPDATE_CATEGORY_OP_200_DESC = "Successful, updating categories";
    public static final String UPDATE_CATEGORY_OP_403_DESC = "Only admin's can update categories";
    public static final String UPDATE_CATEGORY_OP_404_DESC = "Could not find category";
    public static final String UPDATE_CATEGORY_BY_ID = "Update specific category";
    public static final String UPDATE_CATEGORY_OP_403_JSON_MSG =
            PREFIX_JSON_MSG + UPDATE_CATEGORY_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_CATEGORY_BY_ID_OP_DESC = "Retrieve specific category";
    public static final String GET_CATEGORY_BY_ID_OP_200_DESC = "Successful, returning requested category";
    public static final String GET_CATEGORY_BY_ID_OP_403_DESC = "Only user's can retrieve a specific category";
    public static final String GET_CATEGORY_BY_ID_OP_404_DESC = "Requested category is not found";
    public static final String GET_CATEGORY_OP_403_DESC_JSON_MSG =
            PREFIX_JSON_MSG + GET_CATEGORY_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    public static final String ADD_CATEGORY_OP_DESC = "Add to list of Categories";
    public static final String ADD_CATEGORY_OP_200_DESC = "Successful, adding categories";
    public static final String ADD_CATEGORY_OP_403_DESC = "Only admin's can add categories";
    public static final String ADD_CATEGORY_OP_404_DESC = "Could not add category";

    public static final String DELETE_CATEGORY_BY_ID = "Removes category by id";
    public static final String DELETE_CATEGORY_BY_ID_OP_200 = "Successful, deleting defined category";
    public static final String DELETE_CATEGORY_BY_ID_OP_403 = "Only admin can delete defined category";
}