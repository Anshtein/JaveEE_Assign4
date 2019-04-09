package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="Dali", date="2019-04-09T02:27:54.439+0000")
=======
@Generated(value="Dali", date="2019-04-08T17:23:37.210+0000")
>>>>>>> 53db79b502f34a32c29e1465cde15fa3d517d42b
@StaticMetamodel(Customer.class)
public class Customer_ extends ModelBase_ {
	public static volatile SingularAttribute<Customer, Contact> contact;
	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
<<<<<<< HEAD
	public static volatile SingularAttribute<Customer, Contact> contact;
	public static volatile ListAttribute<Customer, Payment> cards;
	public static volatile ListAttribute<Customer, Invoice> invoice;
	public static volatile SingularAttribute<Customer, PlatformUser> user;
=======
	public static volatile ListAttribute<Customer, Payment> payment;
>>>>>>> 53db79b502f34a32c29e1465cde15fa3d517d42b
}
