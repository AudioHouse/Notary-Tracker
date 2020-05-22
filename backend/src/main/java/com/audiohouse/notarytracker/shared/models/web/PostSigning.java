package com.audiohouse.notarytracker.shared.models.web;

import java.util.Date;

public class PostSigning {

    private String clientSurname;
    private String escrowCompanyName;
    private String escrowOfficerName;
    private String zipCodeArea;
    private String clientPhoneNumber;
    private Date signingTimeDate;
    private String additionalNotes;

    public String getClientSurname() {
        return clientSurname;
    }

    public String getEscrowCompanyName() {
        return escrowCompanyName;
    }

    public String getEscrowOfficerName() {
        return escrowOfficerName;
    }

    public String getZipCodeArea() {
        return zipCodeArea;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public Date getSigningTimeDate() {
        return signingTimeDate;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    @Override
    public String toString() {
        return "PostSigning{" +
                "clientSurname='" + clientSurname + '\'' +
                ", escrowCompanyName='" + escrowCompanyName + '\'' +
                ", escrowOfficerName='" + escrowOfficerName + '\'' +
                ", zipCodeArea='" + zipCodeArea + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", signingTimeDate=" + signingTimeDate +
                ", additionalNotes='" + additionalNotes + '\'' +
                '}';
    }
}
