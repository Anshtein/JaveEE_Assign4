<<<<<<< HEAD
package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-09T02:16:41.241+0000")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {
	public static volatile SingularAttribute<Cart, Integer> quantity;
	public static volatile ListAttribute<Cart, Product> products;
	public static volatile SingularAttribute<Cart, Invoice> invoice;
}
=======
package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T17:18:25.978+0000")
@StaticMetamodel(Cart.class)
public class Cart_ extends ModelBase_ {
	public static volatile ListAttribute<Cart, Product> products;
	public static volatile SingularAttribute<Cart, Integer> quantity;
}
>>>>>>> 53db79b502f34a32c29e1465cde15fa3d517d42b
