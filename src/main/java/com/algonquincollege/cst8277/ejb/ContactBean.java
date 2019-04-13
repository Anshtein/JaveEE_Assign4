package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Contact;

@Stateless
public class ContactBean {
    
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    
    public List<Contact> getContactsList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        cq.select(cq.from(Contact.class));
        return em.createQuery(cq).getResultList();
    }

    public Contact getContactByCustomerID() {
        
        return null;
    }
    
    public List<Contact> getContactByCriteria(String keyword) {
        
        return null;
    }
    
    public boolean addContact() {
        
        return true;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Contact updateContact(Contact contactWithUpdatedFields) {
        em.merge(contactWithUpdatedFields);
        return em.find(Contact.class, contactWithUpdatedFields.getId());
    }
    
}
