/********************************************************************egg***m******a**************n************
 * File: PlatformRole.java
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

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role class used for (JSR-375) Java EE Security authorization/authentication
 *  It demonstrates ManyToMany relationship with with PlatformUser table
 */
@Entity
@Table(name="PLATFORM_ROLE")
public class PlatformRole extends ModelBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public PlatformRole() {
        super();
    }

    /**
     * name of a role
     */
    protected String roleName;
    /**
     * list of PlatformUser
     */
    protected List<PlatformUser> platformUsers;

    /**
     * getter for a role name
     * @return String roleName
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * setter for a role name
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * getter for a list of PlatformUser
     * annotated with ManyToMany to represent relationship with PlatformUser table
     * @return List of platformUsers
     */
    @ManyToMany(mappedBy="platformRoles")
    public List<PlatformUser> getPlatformUsers() {
        return platformUsers;
    }
    
    /**
     * setter for a list of PlatformUser
     * @param platformUsers
     */
    public void setPlatformUsers(List<PlatformUser> platformUsers) {
        this.platformUsers = platformUsers;
    }


}