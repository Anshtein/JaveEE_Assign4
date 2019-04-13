package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-12T18:19:37.887+0000")
@StaticMetamodel(Payment.class)
public class Payment_ extends ModelBase_ {
	public static volatile SingularAttribute<Payment, Customer> owner;
	public static volatile SingularAttribute<Payment, String> cardType;
	public static volatile SingularAttribute<Payment, String> cardNum;
	public static volatile SingularAttribute<Payment, String> cardExpiry;
	public static volatile SingularAttribute<Payment, String> cardCCV;
}
