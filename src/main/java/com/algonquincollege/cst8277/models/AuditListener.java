/********************************************************************egg***m******a**************n************
 * File: AuditListener.java
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

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Class for generating local date and time 
 */
public class AuditListener {

    /**
     * creates local date and time when records are about to be created in database
     * @param auditable
     */
    @PrePersist
    public void setCreatedDate(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setCreatedDate(LocalDateTime.now());
    }
    /**
     * updates local date and time when records are about to be updated in database
     * @param auditable
     */
    @PreUpdate
    public void setUpdatedDate(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setUpdatedDate(LocalDateTime.now());
    }
}