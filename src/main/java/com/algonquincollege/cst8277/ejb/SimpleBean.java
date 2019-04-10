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
import com.algonquincollege.cst8277.models.Customer;


@Stateless
public class SimpleBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    public List<Customer> getEmployeeList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        cq.select(cq.from(Customer.class));
        return em.createQuery(cq).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateEmployee(Customer empWithUpdatedFields) {
        em.merge(empWithUpdatedFields);
    }

    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }
    
    public boolean addCustomer(String firstName, String lastName) {
    	if (!firstName.isEmpty() && !lastName.isEmpty()) {
	    	Customer newCustomer = new Customer();
	    	newCustomer.setFirstName(firstName);
	    	newCustomer.setLastName(lastName);
	    	
	    	em.persist(newCustomer);
	    	
	    	if(em.contains(newCustomer))
	    		return true;
	    	else return false;
    	}
    	return false;
    }
    
    public boolean deleteCustomer(Customer customer) {
    	if (!em.contains(customer)) {
    	    customer = em.merge(customer);
    	}
    	em.remove(customer);
    	
    	if(em.contains(customer))
    		return false;
    	else return true;	
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Customer updateCustomer(Customer customerWithUpdatedFields) {
        em.merge(customerWithUpdatedFields);
        return em.find(Customer.class, customerWithUpdatedFields.getId());
    }
}