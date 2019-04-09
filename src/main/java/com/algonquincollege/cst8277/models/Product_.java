package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-09T11:48:42.893+0000")
@StaticMetamodel(Product.class)
public class Product_ extends ModelBase_ {
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, Category> category;
	public static volatile ListAttribute<Product, Cart> carts;
}
