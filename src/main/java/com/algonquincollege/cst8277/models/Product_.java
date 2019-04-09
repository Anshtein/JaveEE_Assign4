<<<<<<< HEAD
package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-09T03:22:19.955+0000")
@StaticMetamodel(Product.class)
public class Product_ extends ModelBase_ {
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile ListAttribute<Product, Cart> carts;
	public static volatile ListAttribute<Product, Category> categories;
}
=======
package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T17:18:26.116+0000")
@StaticMetamodel(Product.class)
public class Product_ extends ModelBase_ {
	public static volatile ListAttribute<Product, Cart> carts;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, Category> category;
}
>>>>>>> 53db79b502f34a32c29e1465cde15fa3d517d42b
