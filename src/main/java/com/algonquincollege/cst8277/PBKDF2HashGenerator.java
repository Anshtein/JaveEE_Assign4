/********************************************************************egg***m******a**************n************
 * File: Audit.java
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
package com.algonquincollege.cst8277;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_KEY_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_SALT_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_KEYSIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_SALTSIZE;

import java.util.HashMap;
import java.util.Map;

import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
/**
 * class for generating password hash 
 */
public class PBKDF2HashGenerator {

    public static void main(String[] args) {

        Pbkdf2PasswordHash pbAndjPasswordHash = new Pbkdf2PasswordHashImpl();

        Map<String, String> pbAndjProperties = new HashMap<>();
        pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
        pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
        pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
        pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
        pbAndjPasswordHash.initialize(pbAndjProperties);
        String pwHash = pbAndjPasswordHash.generate(args[0].toCharArray());
        System.out.printf("Hash for %s is %s%n", args[0], pwHash);
    }
}
