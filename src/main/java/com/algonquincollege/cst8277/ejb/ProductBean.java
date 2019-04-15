/********************************************************************egg***m******a**************n************
 * File: ProductBean.java
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

import com.algonquincollege.cst8277.models.Product;

/**
 * Stateless session bean containing business logic associated with Product entity 
 */
@Stateless
public class ProductBean {

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * adds a product
     * annotated with @TransactionAttribute to specify 
     * whether the container is to invoke a business method within a transaction context
     * @param prodWithUpdatedFields
     * @return int id of the product
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addProduct(Product prodWithUpdatedFields) {
        em.merge(prodWithUpdatedFields);
        return prodWithUpdatedFields.getId();
    }
    
    /**
     * updates product
     * @param prodWithUpdatedFields
     * @return int id of the product
     */
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

    /**
     * finds list of products
     * @return List of Product
     */
    public List<Product> getProductList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * find product by id
     * @param id
     * @return Product product
     */
    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }
    
    /**
     * finds list of product of a certain category
     * @param id
     * @return List of Product
     */
    public List<Product> getProductByCategoryId(int id) {
        Query q = em.createNativeQuery("SELECT * FROM PRODUCT P, CATEGORY_PROD CP " + 
                "WHERE P.ID = CP.PROD_ID AND CP.CATEGORY_ID = ?");
        q.setParameter(1, id);
        
        List<Product> products = q.getResultList();
        
        return products;
    }
}