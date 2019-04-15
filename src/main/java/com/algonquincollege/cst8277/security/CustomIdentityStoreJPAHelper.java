/********************************************************************egg***m******a**************n************
 * File: CustomIdentityStoreJPAHelper.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 *
 */
package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.NAME_PARAM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;
import static java.util.Collections.emptySet;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;

/**
 * helper class for validating customer credentials
 */
@ApplicationScoped
@Default
public class CustomIdentityStoreJPAHelper {

    /**
     * EntityManager injected into the bean
     */
    @PersistenceContext(name = PU_NAME)
    protected EntityManager em;

    /**
     * Pbkdf2PasswordHash injection
     */
    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    /**
     * finds user by name
     * @param username
     * @return PlatformUser platformUser
     */
    public PlatformUser findUserByName(String username) {
        PlatformUser platformUser = null;
        try {
            TypedQuery<PlatformUser> q = em.createNamedQuery(FIND_PLATFORM_USER_BY_NAME_QUERYNAME, PlatformUser.class);
            q.setParameter(NAME_PARAM, username);
            platformUser = q.getSingleResult();
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        return platformUser;
    }

    /**
     * finds roles of a user
     * @param username
     * @return set of PlatformRole
     */
    public Set<PlatformRole> findRolesForUser(String username) {
        Set<PlatformRole> foundRoles = emptySet();
        PlatformUser platformUser = findUserByName(username);
        if (platformUser != null) {
            foundRoles = platformUser.getPlatformRoles();
        }
        return foundRoles;
    }

    /**
     * saves PlatformUser
     * @param platformUser
     */
    public void savePlatformUser(PlatformUser platformUser) {
        em.persist(platformUser);

    }
}
