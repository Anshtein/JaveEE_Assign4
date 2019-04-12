package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@EntityListeners({AuditListener.class})
public class Cart extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;    
    
    protected List<Choice> choices;
    protected Customer customer;
    
    public Cart() {
        super();
    }
    
    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Choice> getChoices() {
        return choices;
    }  
    
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
  
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNING_CUST_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        if (!(obj instanceof Cart)) {
            return false;
        }
        Cart other = (Cart)obj;
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
