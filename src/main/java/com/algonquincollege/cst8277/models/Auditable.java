/********************************************************************egg***m******a**************n************
 * File: Audititable.java
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
/**
 * This interface describes getter and setter for Audit object
 */
public interface Auditable {
    /**
     * gets Audit object
     * @return Audit object
     */
    public Audit getAudit();
    /**
     * sets Audit object
     * @param audit
     */
    public void setAudit(Audit audit);
}
