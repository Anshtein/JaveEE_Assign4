/********************************************************************egg***m******a**************n************
 * File: PaymentConstants.java
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
 * interface containing string constants related to Payment
 */
public interface PaymentConstants {

    public static final String PREFIX_JSON_MSG = "{\"message\":\"";
    public static final String SUFFIX_JSON_MSG = "\"}";

    public static final String PRIMARY_KEY_DESC = "primary key";
    public static final String PAYMENT_RESOURCE_NAME =  "payment";
    public static final String CONTACT_RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String CONTACT_RESOURCE_PATH_ID_PATH =  "/{" + CONTACT_RESOURCE_PATH_ID_ELEMENT + "}";

    public static final String GET_PAYMENTS_OP_DESC = "Retrieves list of payments";
    public static final String GET_PAYMENTS_OP_200_DESC = "Successful, returning payments";
    public static final String GET_PAYMENTS_OP_403_DESC = "Only admin's can list all payments";
    public static final String GET_PAYMENTS_OP_404_DESC = "Could not find payments";
    public static final String GET_PAYMENTS_OP_403_JSON_MSG =
        PREFIX_JSON_MSG + GET_PAYMENTS_OP_403_DESC + SUFFIX_JSON_MSG;

    public static final String GET_PAYMENTS_BY_ID_OP_DESC = "Retrieve specific payment";
    public static final String GET_PAYMENTS_BY_ID_OP_200_DESC = "Successful, returning requested payment";
    public static final String GET_PAYMENTS_BY_ID_OP_403_DESC = "Only user's can retrieve a specific payment";
    public static final String GET_PAYMENTS_BY_ID_OP_404_DESC = "Requested payment not found";
    public static final String GET_PAYMENTS_OP_403_DESC_JSON_MSG =
        PREFIX_JSON_MSG + GET_PAYMENTS_BY_ID_OP_403_DESC + SUFFIX_JSON_MSG;
    
}
