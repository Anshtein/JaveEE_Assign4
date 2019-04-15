/********************************************************************egg***m******a**************n************
 * File: Payment.java
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

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Payment class is for accessing, persisting, and managing data
 * associated with Payment entity.
 * It demonstrates OneToOne relationship with Contact table,
 * ManyToOne relationship with Customer table
 */
@Entity
@EntityListeners({AuditListener.class})
public class Payment extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**
     * type of card
     */
    protected String cardType;
    /**
     * card's number
     */
    protected String cardNum;
    /**
     * card's expiry date
     */
    protected String cardExpiry;
    /**
     * card's CCV
     */
    protected String cardCCV;
    /**
     * Customer object (card's owner)
     */
    protected Customer owner;

    /**
     * default constructor
     */
    public Payment() {
        super();
    }

    /**
     * getter for Customer object
     * annotated with ManyToOne to represent relationship with Customer table
     * @return Customer ownner
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNING_CUST_ID")
    public Customer getOwner() {
        return owner;
    }

    /**
     * gets card's type
     * @return String cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * sets card's type
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * gets card's number
     * @return String cardNum
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * sets card's number
     * @param cardNum
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * gets card's expiry date
     * @return String cardExpiry
     */
    public String getCardExpiry() {
        return cardExpiry;
    }

    /**
     * sets card's expiry date
     * @param cardExpiry
     */
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    /**
     * gets card's CCV
     * @return String cardCCV
     */
    public String getCardCCV() {
        return cardCCV;
    }

    /**
     * sets card's CCV
     * @param cardCCV
     */
    public void setCardCCV(String cardCCV) {
        this.cardCCV = cardCCV;
    }

    /**
     * sets owner of the card
     * @param owner
     */
    public void setOwner(Customer owner) {
        this.owner = owner;
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
        if (!(obj instanceof Payment)) {
            return false;
        }
        Payment other = (Payment)obj;
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
        .append(", card type=")
        .append(cardType)
        .append(", card#=")
        .append(cardNum)  
        .append(", expiry date=")
        .append(cardExpiry)
        .append(", CCV=")
        .append(cardCCV)            
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
