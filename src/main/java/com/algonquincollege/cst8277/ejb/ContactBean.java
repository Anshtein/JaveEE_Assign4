/********************************************************************egg***m******a**************n************
 * File: ContactBean.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 *
 */
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

/**
 * Stateless session bean containing business logic associated with Contact entity 
 */
@Stateless
public class ContactBean {
    
    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;
    
    /**
     * finds the list of all contacts
     * @return List of Contact
     */
    public List<Contact> getContactsList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        cq.select(cq.from(Contact.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * finds a contact by customer id
     * @param customerID
     * @return Contact contact
     */
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
    
    /**
     * finds list of contacts by atrribute name and keyword
     * @param attributeName
     * @param keyword
     * @return List of Contact
     */
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
    
    /**
     * checks if the contact was added
     * @return boolean true or false depending on the outcome
     */
    public boolean addContact() { 	
       
        return true;
    }
    
    /**
     * updates contact,
     * annotated with @TransactionAttribute to specify 
     * whether the container is to invoke a business method within a transaction context
     * @param contactWithUpdatedFields
     * @return Contact contact
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Contact updateContact(Contact contactWithUpdatedFields) {
        em.merge(contactWithUpdatedFields);
        return em.find(Contact.class, contactWithUpdatedFields.getId());
    }
    
    /**
     * adds new Contact
     * @param city
     * @param email
     * @param phone
     * @param postalCode
     * @param province
     * @param street
     * @return true if the contact was added, false otherwise
     */
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
    
    /**
     * updates existing customer
     * @param customerid
     * @param contactid
     * @return Customer customer
     */
    public Customer updateCustomerToContact(int customerid, int contactid) { 	
    	Contact contact = em.find(Contact.class, contactid);
    	Customer customer = em.find(Customer.class, customerid);
    	customer.setContact(contact);
    	em.merge(customer);
		return em.find(Customer.class, customer.getId());
    	
    }
    
    /**
     * checks if Contact's values are not empty and updates them
     * @param id
     * @param city
     * @param email
     * @param phone
     * @param postalCode
     * @param province
     * @param street
     * @return Contact contact
     */
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
    
    /**
     * delets contact by customer id
     * @param custID
     * @return true if contact was deleted, false otherwise
     */
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
