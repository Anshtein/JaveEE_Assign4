/********************************************************************egg***m******a**************n************
 * File: Customer.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Customer class
 * The Customer class is for accessing, persisting, and managing data
 * associated with Customer entity.
 * It demonstrates OneToOne relationship with Contact table,
 * OneToMany relationship with Cart table,
 * OneToMany relationship with Payment table,
 * OneToOne relationship with PlatformUser table
 */
@Entity
@EntityListeners({AuditListener.class})
public class Customer extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * first name of a customer
     */
    protected String firstName;
    /**
     * last name of a customer
     */
    protected String lastName;
    /**
     * reference to Contact object (customer's contact)
     */
    protected Contact contact;
    /**
     * list of Payment cards
     */
    protected List <Payment> cards;
    /**
     * list of carts
     */
    protected List <Cart> carts;
    /**
     * reference to PlatformUser object
     */
    protected PlatformUser user;

    /**
     * default constructor
     */
    public Customer() {
        super();
    }

    /**
     * getter for Contact object
     * annotated with OneToOne to represent relationship with Contact table
     * @return Contact contact
     */
    @OneToOne(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="CONTACT_ID")
    public Contact getContact() {
        return contact;
    }

    /**
     * gets User
     * annotated with OneToOne to represent relationship with PlatformUser table
     * @return PlatformUser user
     */
    @OneToOne(orphanRemoval=true, cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    public PlatformUser getUser() {
        return user;
    }

    /**
     * sets PlatformUser
     * @param user
     */
    public void setUser(PlatformUser user) {
        this.user = user;
    }
    /**
     * sets contact
     * @param contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * getter for a list of Payment
     * annotated with OneToMany to represent relationship with Payment table
     * @return list of cards
     */
    @JsonbTransient
    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Payment> getCards() {
        return cards;
    }

    /**
     * sets list of Payment
     * @param cards
     */
    public void setCards(List<Payment> cards) {
        this.cards = cards;
    }

    /**
     * gets list of Cart
     * annotated with OneToMany to represent relationship with Cart table
     * @return list of carts
     */
    @JsonbTransient
    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Cart> getCarts() {
        return carts;
    }

    /**
     * sets list of Cart
     * @param carts
     */
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }    



    /**
     * gets first name of a customer
     * @return StringfirstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets first name of a customer
     * @param fName
     */
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    /**
     * gets last name of a customer
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets last name of a customer
     * @param lName
     */
    public void setLastName(String lName) {
        this.lastName = lName;
    }

    /**
     * returns a unique integer value for the object at runtime
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * verifies the equality of two objects
     */
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

    /**
     * returns the string representation of the object
     */
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