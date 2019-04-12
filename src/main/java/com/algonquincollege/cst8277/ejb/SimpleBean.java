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

    public Customer getEmployeeById(int id) {
        return em.find(Customer.class, id);
    }
}