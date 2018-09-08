package model;

import java.util.Objects;

public class BeanAppointmentDetail {
    private Integer detailId;
    private Integer times;
    private BeanAppointment appointment;
    private BeanService service;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanAppointmentDetail that = (BeanAppointmentDetail) o;
        return Objects.equals(detailId, that.detailId) &&
                Objects.equals(times, that.times);
    }

    @Override
    public int hashCode() {

        return Objects.hash(detailId, times);
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
}
