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
import javax.persistence.metamodel.SingularAttribute;

import com.algonquincollege.cst8277.models.Contact;
import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformRole_;
import com.algonquincollege.cst8277.models.PlatformUser;

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
    
    public boolean addContact(String city, String email, String phone, String postalCode, String province, String street) {
        if (!city.isEmpty() || !email.isEmpty() || !phone.isEmpty() || !postalCode.isEmpty() || !province.isEmpty() || !street.isEmpty()) {
                            
            Contact newContact = new Contact();
            newContact.setCity(city);
            newContact.setEmail(email);
            newContact.setPhone(phone);
            newContact.setPostalCode(postalCode);
            newContact.setProvince(province);
            newContact.setStreet(street);
            
            em.persist(newContact);
                            
            if(em.contains(newContact))
                return true;
            else return false;
        }
        return false;
    }
    
    public Customer updateCustomerToContact(int customerid, int contactid) { 	
    	Contact contact = em.find(Contact.class, contactid);
    	Customer customer = em.find(Customer.class, customerid);
    	customer.setContact(contact);
    	em.merge(customer);
		return em.find(Customer.class, customer.getId());
    	
    }
    
    public Contact updateContact(String id, String city, String email, String phone, String postalCode, String province, String street) { 	
    	if (!city.isEmpty() || !email.isEmpty() || !phone.isEmpty() || !postalCode.isEmpty() || !province.isEmpty() || !street.isEmpty()) {
            
    		int cid = Integer.parseInt(id);
    		
            Contact newContact = em.find(Contact.class, cid);
            newContact.setCity(city);
            newContact.setEmail(email);
            newContact.setPhone(phone);
            newContact.setPostalCode(postalCode);
            newContact.setProvince(province);
            newContact.setStreet(street);
            
            em.merge(newContact);
    		return em.find(Contact.class, newContact.getId());
            
        }
        return null;
    }
    
    
    public boolean deleteContactByCustID(int custID) {
    	
    	Customer customer = em.find(Customer.class, custID);
    	Contact contact = customer.getContact();
    	customer.setContact(null);
    	em.merge(customer);
    	
        em.remove(contact);
        
        if(em.contains(contact))
            return false;
        return true;   
    }
    
}