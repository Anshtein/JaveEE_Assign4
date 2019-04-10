package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformRole_;



@Stateless
public class SimpleBean {

	@Inject
	protected BuildUser buildUser;
	
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
	    	
	    	String userPassword = "temppwd";
	    	String customerRole = "customer";
	    	
	    	CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<PlatformRole> cq = cb.createQuery(PlatformRole.class);
	        Root<PlatformRole> root = cq.from(PlatformRole.class);       
	        cq.select(root);
	        cq.where(cb.equal(root.get(PlatformRole_.roleName), "customer"));
	        TypedQuery<PlatformRole> tq = em.createQuery(cq);
	        List<PlatformRole> role = tq.getResultList();  
	    	
	    	buildUser.buildUser(firstName+lastName, userPassword, role);
	    	
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