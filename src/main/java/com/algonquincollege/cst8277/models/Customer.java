/********************************************************************egg***m******a**************n************
 * File: Employee.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * (Modified) @date 2019 03
 *
 * Copyright (c) 1998, 2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Original @authors dclarke, mbraeuer
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Employee class
 */
@Entity
@EntityListeners({AuditListener.class})
public class Customer extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected Contact contact;
    protected List <Payment> cards;
    protected List <Cart> carts;
    protected PlatformUser user;
    
    public Customer() {
        super();
    }

    //TODO - 1:1 relationship to core entity

    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="CONTACT_ID")
    public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
    

    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Payment> getCards() {
        return cards;
    }

    public void setCards(List<Payment> cards) {
        this.cards = cards;
    }

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }    
    
    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    public PlatformUser getUser() {
        return user;
    }

    public void setUser(PlatformUser user) {
        this.user = user;
    }

	

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lName) {
        this.lastName = lName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Employee [id=")
            .append(id)
            .append(", firstName=")
            .append(firstName)
            .append(", lastName=")
            .append(lastName)
            .append(", version=")
            .append(version)
            .append(", created=")
            .append(audit.getCreatedDate())
            .append(", updated=")
            .append(audit.getUpdatedDate())
            .append("]")
        ;
        return builder.toString();
    }

}