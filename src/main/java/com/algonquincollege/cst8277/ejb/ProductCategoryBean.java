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
import com.algonquincollege.cst8277.models.Product;

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
        
        if(category != null && category.getId() > 0) {
            /*em.createNativeQuery("DELETE FROM CHOICE " + 
                    "WHERE PRODUCT_ID = " + id).executeUpdate();
            
            em.createNativeQuery("DELETE FROM PRODUCT " + 
                    "WHERE ID = " + id).executeUpdate();*/
            
            em.createNativeQuery("DELETE FROM CATEGORY_PROD " + 
                    "WHERE CATEGORY_ID = " + id).executeUpdate();
                        
            em.createNativeQuery("DELETE FROM CATEGORY " + 
                    "WHERE ID = " + id).executeUpdate();

        }       
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCategoryById(int id) {
        Category category = em.find(Category.class, id);
        em.merge(category);
    }
}