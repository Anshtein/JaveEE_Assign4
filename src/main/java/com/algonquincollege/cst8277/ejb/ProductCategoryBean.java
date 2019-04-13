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

import com.algonquincollege.cst8277.models.Category;

@Stateless
public class ProductCategoryBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addCategory(Category catWithUpdatedFields) {
        em.persist(catWithUpdatedFields);
        return catWithUpdatedFields.getId();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int updateCategory(Category catWithUpdatedFields) {
        em.merge(catWithUpdatedFields);
        return catWithUpdatedFields.getId();
    }

    public List<Category> getCategoryList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        cq.select(cq.from(Category.class));
        return em.createQuery(cq).getResultList();
    }

    public Category getCategoryById(int id) {
        return em.find(Category.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCategoryById(int id) {
        Category category = em.find(Category.class, id);
        em.remove(category);        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCategoryById(int id) {
        Category category = em.find(Category.class, id);
        em.merge(category);
    }
}