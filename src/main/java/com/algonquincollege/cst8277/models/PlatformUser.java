/********************************************************************egg***m******a**************n************
 * File: PlatformUser.java
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
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.ID_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_INVERSEJOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_COLUMN_NAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PLATFORM_USER_JOIN_TABLE_NAME;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.algonquincollege.cst8277.utils.RestDemoConstants;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 * It demonstrates ManyToMany relationship with with PlatformRole table
 */
@Entity
@Table(name=RestDemoConstants.PLATFORM_USER_TABLE_NAME)
@NamedQueries(
    @NamedQuery(
        name=RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME,
        query=RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_JPQL
    )
)
public class PlatformUser extends ModelBase implements Serializable, Principal {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public PlatformUser() {
        super();
    }

    /**
     * user name
     */
    protected String username;
    /**
     * user password hash
     */
    protected String pwHash;
    /**
     * set of roles that a user is allowed to take on
     */
    protected Set<PlatformRole> platformRoles = new HashSet<>();
    /**
     * Customer object
     */
    protected Customer customer;

    /**
     * getter of a user's name
     * return String username
     */
    @Override
    public String getName() {
        return username;
    }
    
    /**
     * getter of a user's  name 
     * @return String username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * sets user's name
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password hash
     * @return String pwHash
     */
    public String getPwHash() {
        return pwHash;
    }
    
    /**
     * sets password hash
     * @param pwHash
     */
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    /**
     * getter for the list of PlatformRole
     * annotated with ManyToMany to represent relationship with PlatformRole table
     * using an intermediary entity PLATFORM_USER_ROLE for the join table
     * @return set of PlatformRole
     */
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name=PLATFORM_USER_JOIN_TABLE_NAME,
      joinColumns=@JoinColumn(name=PLATFORM_USER_JOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME),
      inverseJoinColumns=@JoinColumn(name=PLATFORM_USER_INVERSEJOIN_COLUMN_NAME, referencedColumnName=ID_COLUMN_NAME))
    public Set<PlatformRole> getPlatformRoles() {
        return platformRoles;
    }
    
    /**
     * sets Platform roles
     * @param platformRoles
     */
    public void setPlatformRoles(Set<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
    }
    
    
    /**
     * gets Customer object
     * mapps OneToOne to represent relationship with Customer table
     * @return Customer customer
     */
    @OneToOne(orphanRemoval=true, mappedBy="user", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * sets Customer object
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    
}