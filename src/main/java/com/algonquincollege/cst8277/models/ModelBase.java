/********************************************************************egg***m******a**************n************
 * File: ModelBase.java
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

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract class that is base of (class) hierarchy for all com.algonquincollege.cst8277.models @Entity classes
 */
@MappedSuperclass
public abstract class ModelBase implements Auditable {

    /**
     * id of an entity
     */
    protected int id;
    /**
     * version of an entity
     */
    protected int version;
    /**
     * Audit object inherited by an entity
     */
    protected Audit audit = new Audit();

    /**
     * no-args constructor
     */
    public ModelBase() {
        super();
        this.audit.setCreatedDate(LocalDateTime.now());
        // created_date and updated_date have the same value at the very beginning of object's lifecycle
        this.audit.setUpdatedDate(this.audit.getCreatedDate());
    }

    /**
     * gets auto-generated id of an entity
     * @return int id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    /**
     * sets id of an entity
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets verstion of an entity
     * @return int version
     */
    @Version
    public int getVersion() {
        return version;
    }
    /**
     * sets verstion of an entity
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * gets Audit object
     * return Audit audit
     */
    public Audit getAudit() {
        return audit;
    }
    /**
     * sets Audit object
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}