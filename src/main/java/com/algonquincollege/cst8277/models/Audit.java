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
package com.algonquincollege.cst8277.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeddable class containing created date and updated date
 * for the corresponding columns
 */
@Embeddable
public class Audit {

    /**
     * LocalDateTime object for creating new date
     */
    protected LocalDateTime createdDate;
    /**
     * LocalDateTime object for updating a date
     */
    protected LocalDateTime updatedDate;

    /**
     * default constructor
     */
    public Audit() {
    }

    /**
     * getter for the createdDate object
     * @return LocalDateTime createdDate
     */
    @Column(name = "CREATED_DATE")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    /**
     * setter for the createdDate object
     * @param createdDate
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * getter for the updatedDate object
     * @return LocalDateTime updatedDate
     */
    @Column(name = "UPDATED_DATE")
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    /**
     * setter for the updatedDate object
     * @param updatedDate
     */
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

}