package com.aptit.octagnosis.req;

import com.aptit.octagnosis.model.Acunt;
import com.aptit.octagnosis.model.Personal;
import lombok.Data;

@Data
public class RegistrationRequest {
    private Acunt acunt;
    private Personal personal;

    public Acunt getAcunt() {
        return acunt;
    }

    public void setAcunt(Acunt acunt) {
        this.acunt = acunt;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
