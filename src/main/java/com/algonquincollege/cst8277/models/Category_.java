package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-09T02:24:11.445+0000")
@StaticMetamodel(Category.class)
public class Category_ extends ModelBase_ {
	public static volatile SingularAttribute<Category, String> name;
	public static volatile ListAttribute<Category, Product> product;
}
