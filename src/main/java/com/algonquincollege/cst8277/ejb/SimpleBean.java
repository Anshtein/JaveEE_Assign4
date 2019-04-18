/********************************************************************egg***m******a**************n************
 * File: SimpleBean.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
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


/**
 * Stateless session bean containing business logic associated with Customer entity 
 */
@Stateless
public class SimpleBean {

    /**
     * injects BuildUser bean
     */
    @Inject
    protected BuildUser buildUser;

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * finds the list of all customers
     * @return List of Customer
     */
    public List<Customer> getCustomerList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        cq.select(cq.from(Customer.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     *  finds a customer by id
     * @param id
     * @return Customer customer
     */
    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }

    /**
     * checks if username of a customer found is equal to the one passed in parameters
     * @param username
     * @param id
     * @return true if it is equal, false otherwise
     */
    public boolean checkCustomerUsernameId(String username, int id) {
        Customer customer = getCustomerById(id);
        PlatformUser pu = customer.getUser();
        String customerUsername = pu.getUsername();
        if(customerUsername.equals(username)) {
            return true;
        }
        return false;

    }

    /**
     * adds a new customer
     * @param firstName
     * @param lastName
     * @return true if customer was added, false otherwise
     */
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

    /**
     * deletes customer
     * @param customer
     * @return true if customer was deleted, false otherwise
     */
    public boolean deleteCustomer(Customer customer) {
        if (!em.contains(customer)) {
            customer = em.merge(customer);
        }
        em.remove(customer);

        if(em.contains(customer))
            return false;
        else return true;   
    }

    /**
     * updates Customer
     * annotated with @TransactionAttribute to specify 
     * whether the container is to invoke a business method within a transaction context
     * @param customerWithUpdatedFields
     * @return Customer customer
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Customer updateCustomer(Customer customerWithUpdatedFields) {
        em.merge(customerWithUpdatedFields);
        return em.find(Customer.class, customerWithUpdatedFields.getId());
    }
}