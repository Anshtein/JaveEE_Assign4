/********************************************************************egg***m******a**************n************
 * File: ProductCategoryBean.java
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.algonquincollege.cst8277.models.Category;
import com.algonquincollege.cst8277.models.Product;

/**
 * Stateless session bean containing business logic associated with Category entity 
 */
@Stateless
public class ProductCategoryBean {

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     * adds a category
     * annotated with @TransactionAttribute to specify 
     * whether the container is to invoke a business method within a transaction context
     * @param catWithUpdatedFields
     * @return id of the category added
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addCategory(Category catWithUpdatedFields) {
        em.persist(catWithUpdatedFields);
        return catWithUpdatedFields.getId();
    }

    /**
     * updates a category
     * @param catWithUpdatedFields
     * @return id of the updated category
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int updateCategory(Category catWithUpdatedFields) {
        em.merge(catWithUpdatedFields);
        return catWithUpdatedFields.getId();
    }

    /**
     * finds list of all categories
     * @return List of Category
     */
    public List<Category> getCategoryList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        cq.select(cq.from(Category.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * finds category by id
     * @param id
     * @return Category category
     */
    public Category getCategoryById(int id) {
        return em.find(Category.class, id);
    }

    /**
     * deletes category
     * @param id
     */
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

    /**
     * updates category
     * @param id
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCategoryById(int id) {
        Category category = em.find(Category.class, id);
        em.merge(category);
    }
}