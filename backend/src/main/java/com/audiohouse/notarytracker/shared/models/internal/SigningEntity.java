package com.audiohouse.notarytracker.shared.models.internal;

import java.io.Serializable;
import java.util.Date;

public class SigningEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    private String signingId;
    private String clientSurname;
    private String escrowCompanyName;
    private String escrowOfficerName;
    private String zipCodeArea;
    private String clientPhoneNumber;
    private String assignedNotaryId;
    private String assignedNotaryName;
    private Date signingTimeDate;
    private Date createdDate;
    private Date lastUpdatedDate;
    private String additionalNotes;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSigningId() {
        return signingId;
    }

    public void setSigningId(String signingId) {
        this.signingId = signingId;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getEscrowCompanyName() {
        return escrowCompanyName;
    }

    public void setEscrowCompanyName(String escrowCompanyName) {
        this.escrowCompanyName = escrowCompanyName;
    }

    public String getEscrowOfficerName() {
        return escrowOfficerName;
    }

    public void setEscrowOfficerName(String escrowOfficerName) {
        this.escrowOfficerName = escrowOfficerName;
    }

    public String getZipCodeArea() {
        return zipCodeArea;
    }

    public void setZipCodeArea(String zipCodeArea) {
        this.zipCodeArea = zipCodeArea;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getAssignedNotaryId() {
        return assignedNotaryId;
    }

    public void setAssignedNotaryId(String assignedNotaryId) {
        this.assignedNotaryId = assignedNotaryId;
    }

    public String getAssignedNotaryName() {
        return assignedNotaryName;
    }

    public void setAssignedNotaryName(String assignedNotaryName) {
        this.assignedNotaryName = assignedNotaryName;
    }

    public Date getSigningTimeDate() {
        return signingTimeDate;
    }

    public void setSigningTimeDate(Date signingTimeDate) {
        this.signingTimeDate = signingTimeDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    @Override
    public String toString() {
        return "SigningEntity{" +
                "signingId='" + signingId + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", escrowCompanyName='" + escrowCompanyName + '\'' +
                ", escrowOfficerName='" + escrowOfficerName + '\'' +
                ", zipCodeArea='" + zipCodeArea + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", assignedNotaryId='" + assignedNotaryId + '\'' +
                ", assignedNotaryName='" + assignedNotaryName + '\'' +
                ", signingTimeDate=" + signingTimeDate.toString() +
                ", createdDate=" + createdDate.toString() +
                ", lastUpdatedDate=" + lastUpdatedDate.toString() +
                '}';
    }
}
