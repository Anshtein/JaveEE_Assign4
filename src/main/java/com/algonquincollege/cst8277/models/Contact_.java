package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="Dali", date="2019-04-09T02:18:15.431+0000")
=======
@Generated(value="Dali", date="2019-04-08T17:18:26.470+0000")
>>>>>>> 53db79b502f34a32c29e1465cde15fa3d517d42b
@StaticMetamodel(Contact.class)
public class Contact_ extends ModelBase_ {
	public static volatile SingularAttribute<Contact, String> email;
	public static volatile SingularAttribute<Contact, String> phone;
	public static volatile SingularAttribute<Contact, String> street;
	public static volatile SingularAttribute<Contact, String> city;
	public static volatile SingularAttribute<Contact, String> province;
	public static volatile SingularAttribute<Contact, String> postalCode;
	public static volatile SingularAttribute<Contact, Customer> customer;
}
