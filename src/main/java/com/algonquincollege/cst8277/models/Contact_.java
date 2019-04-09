package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-09T04:36:21.785+0000")
@StaticMetamodel(Contact.class)
public class Contact_ extends ModelBase_ {
	public static volatile SingularAttribute<Contact, Customer> customer;
	public static volatile SingularAttribute<Contact, String> email;
	public static volatile SingularAttribute<Contact, String> phone;
	public static volatile SingularAttribute<Contact, String> street;
	public static volatile SingularAttribute<Contact, String> city;
	public static volatile SingularAttribute<Contact, String> province;
	public static volatile SingularAttribute<Contact, String> postalCode;
}
