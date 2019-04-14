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

import com.algonquincollege.cst8277.models.Product;

@Stateless
public class ProductBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addProduct(Product prodWithUpdatedFields) {
        em.merge(prodWithUpdatedFields);
        return prodWithUpdatedFields.getId();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int updateProduct(Product prodWithUpdatedFields) {
        em.merge(prodWithUpdatedFields);
        return prodWithUpdatedFields.getId();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteProductById(int id) {
        Product product = em.find(Product.class, id);
        
        if(product != null && product.getId() > 0) {
            em.createNativeQuery("DELETE FROM CHOICE " + 
                    "WHERE PRODUCT_ID = " + id).executeUpdate();
            
            em.createNativeQuery("DELETE FROM CATEGORY_PROD " + 
                    "WHERE PROD_ID = " + id).executeUpdate();
            
            em.createNativeQuery("DELETE FROM PRODUCT " + 
                    "WHERE ID = " + id).executeUpdate();

        }
    }

    public List<Product> getProductList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }

    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }
    
    //TODO
    public List<Product> getProductByCategoryId(int id) {
        Query q = em.createNativeQuery("SELECT * FROM PRODUCT P, CATEGORY_PROD CP " + 
                "WHERE P.ID = CP.PROD_ID AND CP.CATEGORY_ID = ?");
        q.setParameter(1, id);
        
        List<Product> products = q.getResultList();
        
        return products;
    }
}