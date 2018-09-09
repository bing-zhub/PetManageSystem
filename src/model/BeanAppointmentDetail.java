package model;

import java.util.Date;
import java.util.Objects;

public class BeanAppointmentDetail {
    private Integer detailId;
    private Date app_date;
    private BeanAppointment appointment;
    private BeanService service;
    private BeanPet pet;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanAppointmentDetail that = (BeanAppointmentDetail) o;
        return Objects.equals(detailId, that.detailId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(detailId);
    }

    public BeanAppointment getAppointment() {
        return appointment;
    }

    public void setAppointment(BeanAppointment appointment) {
        this.appointment = appointment;
    }

    public BeanService getService() {
        return service;
    }

    public void setService(BeanService service) {
        this.service = service;
    }

    public Date getApp_date() {
        return app_date;
    }

    public void setApp_date(Date app_date) {
        this.app_date = app_date;
    }

    public BeanPet getPet() {
        return pet;
    }

    public void setPet(BeanPet pet) {
        this.pet = pet;
    }
}
