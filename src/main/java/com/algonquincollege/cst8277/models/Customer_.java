package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T17:23:37.210+0000")
@StaticMetamodel(Customer.class)
public class Customer_ extends ModelBase_ {
	public static volatile SingularAttribute<Customer, Contact> contact;
	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile ListAttribute<Customer, Payment> payment;
}
