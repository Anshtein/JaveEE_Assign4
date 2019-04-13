package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-12T18:11:55.948+0000")
@StaticMetamodel(Product.class)
public class Product_ extends ModelBase_ {
	public static volatile SingularAttribute<Product, Choice> choice;
	public static volatile ListAttribute<Product, Category> categories;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Double> price;
}
