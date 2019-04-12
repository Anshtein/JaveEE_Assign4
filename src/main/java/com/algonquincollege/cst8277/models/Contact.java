/********************************************************************egg***m******a**************n************
 * File: Contact.java
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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * The Contact class is for accessing, persisting, and managing data
 * associated with Contact entity.
 * It demonstrates OneToOne relationship with Customer table
 */
@Entity
@EntityListeners({AuditListener.class})
public class Contact extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * customer email
     */
    protected String email;
    /**
     * customer phone
     */
    protected String phone;
    /**
     * customer street
     */
    protected String street;
    /**
     * customer city
     */
    protected String city;
    /**
     * customer province
     */
    protected String province;
    /**
     * customer postalCode
     */
    protected String postalCode;
    /**
     * reference to Customer object
     */
    protected Customer customer;

    /**
     * default constructor
     */
    public Contact() {
        super();
    }

    /**
     * gets Customer object
     * annotated with OneToOne relationship with Customer table
     * @return Customer customer
     */
    @OneToOne(orphanRemoval=true, mappedBy="contact", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    public Customer getCustomer() {
        return customer;
    }

    /**
     * sets customer
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * gets customer email
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets customer email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets customer phone
     * @return String phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets customer phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets customer street
     * @return String street
     */
    public String getStreet() {
        return street;
    }

    /**
     * sets customer street
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * gets customer city
     * @return String city
     */
    public String getCity() {
        return city;
    }

    /**
     * sets customer city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * gets customer province
     * @return String province
     */
    public String getProvince() {
        return province;
    }

    /**
     * sets customer province
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * gets customer postal code
     * @return String postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets customer postal code
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact other = (Contact)obj;
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
        .append("Contact [id=")
        .append(id)
        .append(", email=")
        .append(email)
        .append(", phone#=")
        .append(phone)  
        .append(", street=")
        .append(street)
        .append(", city=")
        .append(city)
        .append(", province=")
        .append(province)
        .append(", postal code=")
        .append(postalCode)
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
