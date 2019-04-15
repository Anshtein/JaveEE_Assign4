package com.algonquincollege.cst8277.models;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerCartTestSuite {
    
    static EntityManagerFactory emp;
    
    @BeforeClass
    public static void oneTimeSetUp() {
        emp = Persistence.createEntityManagerFactory("shopping_cart_jee56");
    }

}
