package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.algonquincollege.cst8277.models.Contact;
import com.algonquincollege.cst8277.models.Customer;

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

    public Contact getContactByCustomerID(int customerID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> address = cq.from(Contact.class);
        Join<Contact, Customer> employee = address.join("employee");

        cq.where(
                cb.equal(employee.get("ID"), customerID));
        TypedQuery<Contact> q = em.createQuery(cq);
        return q.getSingleResult();    
    }
    
    public List<Contact> getContactByCriteria(String attributeName, String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> address = cq.from(Contact.class);
        Join<Contact, Customer> employee = address.join("employee");

        cq.where(
                cb.equal(employee.get(attributeName), keyword));
        TypedQuery<Contact> q = em.createQuery(cq);
        return q.getResultList();
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
