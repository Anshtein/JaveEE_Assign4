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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Choice;

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
     * @param newChoice
     * @return int Choice id
     */
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
        if(choice!=null) {
        em.remove(choice);
            if(!em.contains(choice))
                return true;
        }

        return false;
    }

    public Choice getChoiceById(int id) {
        return em.find(Choice.class, id);
    }


    public List<Choice> getChoicesByCartAndProductId(int cartId, int productId) {
        Query q = em.createNativeQuery("SELECT * FROM CHOICE C WHERE CART_ID = ? AND PRODUCT_ID = ?");
        q.setParameter(1, cartId);
        q.setParameter(2, productId);
        List<Choice> choices = q.getResultList();    
        return choices;
    }



}
