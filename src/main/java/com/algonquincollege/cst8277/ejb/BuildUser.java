/********************************************************************egg***m******a**************n************
 * File: BuildUser.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_ADMIN_USER;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_ADMIN_USER_PASSWORD_PROPNAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_ADMIN_USER_PROPNAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_KEY_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_SALT_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_KEYSIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_SALTSIZE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.algonquincollege.cst8277.models.Customer;
import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;
import com.algonquincollege.cst8277.security.CustomIdentityStoreJPAHelper;

/**
 * class for creating a user
 */
@Startup
@Singleton
public class BuildUser {

    /**
     * CustomIdentityStoreJPAHelper injection
     */
    @Inject
    protected CustomIdentityStoreJPAHelper jpaHelper;

    /**
     * Pbkdf2PasswordHash injection
     */
    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    /**
     * creates user based on user name, password and role
     * @param userName
     * @param userPassword
     * @param customerRole
     * @return PlatformUser user
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public PlatformUser buildUser(String userName, String userPassword, List<PlatformRole> customerRole) {
        PlatformUser user = jpaHelper.findUserByName(userName);
        if (user == null) {
            user = new PlatformUser();
            user.setUsername(userName);
            Map<String, String> pbAndjProperties = new HashMap<>();
            pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
            pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
            pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
            pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
            pbAndjPasswordHash.initialize(pbAndjProperties);
            String pwHash = pbAndjPasswordHash.generate(userPassword.toCharArray());
            user.setPwHash(pwHash);

            Set<PlatformRole> platformRoles = new HashSet<PlatformRole>();
            for (PlatformRole x : customerRole) 
            	platformRoles.add(x); 
            user.setPlatformRoles(platformRoles);
            jpaHelper.savePlatformUser(user);
        }
		return user;
    }
    
       
}