package com.cgi.dentistapp.dto;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by serkp on 2.03.2017.
 */
public class DentistVisitDTO {

    @Size(min = 1, max = 50)
    String dentistName;

    @Size(min = 16, max = 16, message = "Kuupäev peab olema 16 tähemärki pikk")
    String visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, String visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
