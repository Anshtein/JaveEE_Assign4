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

import com.algonquincollege.cst8277.models.Choice;
import com.algonquincollege.cst8277.models.Product;

@Stateless
public class ChoiceBean {
    
//  @Inject
//  protected BuildUser buildUser;
  
  @PersistenceContext(unitName = PU_NAME)
  protected EntityManager em;
  
  public List<Choice> getChoiceList() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Choice> cq = cb.createQuery(Choice.class);
      cq.select(cq.from(Choice.class));
      return em.createQuery(cq).getResultList();   
  }
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void updateChoice(Choice updatedChoice) { 
      em.merge(updatedChoice);
  }

  public Choice getChoiceById(int id) {
      return em.find(Choice.class, id);
  }
  
  public boolean addChoice(Product product) {
          Choice choice = new Choice();
          choice.setProduct(product);    
          em.persist(choice);
 
          if(em.contains(choice))
              return true;
          else 
              return false;
   
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
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Choice updateChoice2(Choice customerWithUpdatedFields) { 
      em.merge(customerWithUpdatedFields);
      return em.find(Choice.class, customerWithUpdatedFields.getId());
  }
  

}
