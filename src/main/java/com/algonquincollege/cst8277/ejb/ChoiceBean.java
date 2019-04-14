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

@Stateless
public class ChoiceBean { 
    
//  @Inject
//  protected BuildUser buildUser;
  
  @PersistenceContext(unitName = PU_NAME)
  protected EntityManager em;
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public int updateChoice(Choice updatedChoice) { 
      em.merge(updatedChoice);
      return updatedChoice.getId();
  }
  
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
  
  public Choice getChoiceById(int id) {
      return em.find(Choice.class, id);
  }
   
  // ?????????????????????????
  //getChoiceByProductId
  //getChoiceByCartId
  //getChoiceByQuantity
  
//public List<Cart> getCartsByCustomerId(int id) {
//Query q = em.createNativeQuery("SELECT * FROM CART C WHERE OWNING_CUST_ID = ?");
//q.setParameter(1, id); 
//List<Cart> carts = q.getResultList();    
//return carts;
//}
  
  

}
