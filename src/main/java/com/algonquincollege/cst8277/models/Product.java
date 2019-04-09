package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@EntityListeners({AuditListener.class})
public class Product extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String name;
    protected double price;    
    protected Choice choice;
    protected List<Category> categories;
    
    
    public Product() {
        super();
    }
        
        
    /**
     * Map OneToOne and get the child entity
     * @return Choice choice
     */
    @OneToOne(orphanRemoval=true, mappedBy="product", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }


    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="CATEGORY_PROD",
    joinColumns=@JoinColumn(name="PROD_ID"),
    inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
            .append("Product [SKU=")
            .append(id)
            .append(", name=")
            .append(name)
            .append(", price=")
            .append(String.valueOf(price))
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
