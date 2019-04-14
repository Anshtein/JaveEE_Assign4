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

import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformRole_;
import com.algonquincollege.cst8277.models.PlatformUser;



@Stateless
public class SimpleBean {

    @Inject
    protected BuildUser buildUser;
    
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    public List<Customer> getCustomerList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        cq.select(cq.from(Customer.class));
        return em.createQuery(cq).getResultList();
    }


    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }
    
    public boolean checkCustomerUsernameId(String username, int id) {
        Customer customer = getCustomerById(id);
        PlatformUser pu = customer.getUser();
        String customerUsername = pu.getUsername();
        if(customerUsername.equals(username)) {
            return true;
        }
        return false;
        
    }
    
    public boolean addCustomer(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            String userPassword = "temppwd";
            String customerRole = "customer";
            
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<PlatformRole> cq = cb.createQuery(PlatformRole.class);
            Root<PlatformRole> root = cq.from(PlatformRole.class);       
            cq.select(root);
            cq.where(cb.equal(root.get(PlatformRole_.roleName), "customer"));
            TypedQuery<PlatformRole> tq = em.createQuery(cq);
            List<PlatformRole> role = tq.getResultList(); 
            
            PlatformUser pu = buildUser.buildUser(firstName+lastName, userPassword, role);

            
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(firstName);
            newCustomer.setLastName(lastName);
            newCustomer.setUser(pu);
            
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