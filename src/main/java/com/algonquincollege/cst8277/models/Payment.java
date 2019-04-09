package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners({AuditListener.class})
public class Payment extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    protected String cardType;
    protected String cardNum;
    protected String cardExpiry;
    protected String cardCCV;
    protected Customer owner;
    
    public Payment() {
        super();
    }

    /**
     * Map ManyToOne and get Employee instance
     * @return
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNING_CUST_ID")
    public Customer getOwner() {
        return owner;
    }
    
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardCCV() {
        return cardCCV;
    }

    public void setCardCCV(String cardCCV) {
        this.cardCCV = cardCCV;
    }



    public void setOwner(Customer owner) {
        this.owner = owner;
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
        if (!(obj instanceof Payment)) {
            return false;
        }
        Payment other = (Payment)obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

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
