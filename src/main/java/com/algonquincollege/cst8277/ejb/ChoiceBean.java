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


@Stateless
public class ChoiceBean { 

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;


    public int addChoice(Choice newChoice) {
        em.persist(newChoice);
        return newChoice.getId();
    }

    public List<Choice> getChoiceList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
        cq.select(cq.from(Choice.class));
        return em.createQuery(cq).getResultList();   
    }

    public int updateChoice(Choice updatedChoice) { 
        em.merge(updatedChoice);
        return updatedChoice.getId();
    }

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
