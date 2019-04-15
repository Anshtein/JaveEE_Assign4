/********************************************************************egg***m******a**************n************
 * File: ChoiceBean.java
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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Choice;
import com.algonquincollege.cst8277.models.Product;

/**
 * Stateless session bean containing business logic associated with Choice entity 
 */
@Stateless
public class ChoiceBean { 

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * adds new choice
     * annotated with @TransactionAttribute to specify 
     * whether the container is to invoke a business method within a transaction context
     * @param newChoice
     * @return int Choice id
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addChoice(Choice newChoice) {
        em.persist(newChoice);
        return newChoice.getId();
    }

    /**
     * finds list of Choice
     * @return List of Choice
     */
    public List<Choice> getChoiceList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        cq.select(cq.from(Choice.class));
        return em.createQuery(cq).getResultList();   
    }

    /**
     * updates choice
     * @param updatedChoice
     * @return int choice id
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int updateChoice(Choice updatedChoice) { 
        em.merge(updatedChoice);
        return updatedChoice.getId();
    }

    /**
     * deletes choice
     * @param choice
     * @return true if success, false otherwise
     */
    public boolean deleteChoice(Choice choice) {
        if (!em.contains(choice)) {
            choice = em.merge(choice);
        }
        em.remove(choice);

        if(em.contains(choice))
            return false;
        else 
            return true;   
    }

    /**
     * finds choice by id
     * @param id
     * @return Choice choice
     */
    public Choice getChoiceById(int id) {
        return em.find(Choice.class, id);
    }


}
