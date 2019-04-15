/********************************************************************egg***m******a**************n************
 * File: CustomIdentityStore.java
 * Course materials (19W) CST 8277
 * @author Mike Norman
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.security;

import static java.util.Collections.emptySet;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;

/**
 * class for validating customer credentials
 */
@ApplicationScoped
@Default
public class CustomIdentityStore implements IdentityStore {

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
     * validates customer credentials
     * return CredentialValidationResult result
     */
    @Override
    public CredentialValidationResult validate(Credential credential) {

        CredentialValidationResult result = INVALID_RESULT;

        if (credential instanceof UsernamePasswordCredential) {
            String callerName = ((UsernamePasswordCredential)credential).getCaller();
            String credentialPassword = ((UsernamePasswordCredential)credential).getPasswordAsString();
            PlatformUser user = jpaHelper.findUserByName(callerName);
            if (user != null) {
                String pwHash = user.getPwHash();
                /*
                 * pwHash is actually a multifield String with ':' as the field separator:
                 *   <algorithm>:<iterations>:<base64(salt)>:<base64(hash)>
                 *
                 *   Pbkdf2PasswordHash.Algorithm (String identifier)
                 *     "PBKDF2WithHmacSHA224" - too small don't use,
                 *     "PBKDF2WithHmacSHA256" - default,
                 *     "PBKDF2WithHmacSHA384" - meh
                 *     "PBKDF2WithHmacSHA512" - best security, but CPU hog (maybe not mobile)
                 *
                 *  Pbkdf2PasswordHash.Iterations (integer)
                 *     1024 - minimum (too small don't use)
                 *     2048 - default
                 *   I have seen 20,000 up to 50,000 in production
                 *
                 */
                try {
                    boolean verified = pbAndjPasswordHash.verify(credentialPassword.toCharArray(), pwHash);
                    if (verified) {
                        Set<String> roleNames = getRolesNamesFromPlatformRoles(user.getPlatformRoles());
                        result = new CredentialValidationResult(callerName, roleNames);
                    }
                }
                catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
        // check if the credential was CallerOnlyCredential
        else if (credential instanceof CallerOnlyCredential) {
            String callerName = ((CallerOnlyCredential)credential).getCaller();
            PlatformUser user = jpaHelper.findUserByName(callerName);
            if (user != null) {
                result = new CredentialValidationResult(callerName);
            }
        }

        return result;
    }

    /**
     * gets all roles from PlatformRole
     * @param platformRoles
     * @return Set of role names
     */
    protected Set<String> getRolesNamesFromPlatformRoles(Set<PlatformRole> platformRoles) {
        Set<String> roleNames = emptySet();
        if (!platformRoles.isEmpty()) {
            roleNames = platformRoles
                .stream()
                .map(s -> s.getRoleName())
                .collect(Collectors.toSet());
        }
        return roleNames;
    }

}