package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeanService {
    private Integer servId;
    private String servName;
    private Integer servPrice;
    private Set<BeanAppointment> appointments = new HashSet<BeanAppointment>();
    public static final String[] tableTitles={"ID","Ãû³Æ","¼Û¸ñ"};

    public String getCell(int col){
        if(col==0) return this.servId.toString();
        else if(col==1) return this.servName.toString();
        else if(col==2) return this.servPrice.toString();
        else return "";
    }

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public Integer getServPrice() {
        return servPrice;
    }

    public void setServPrice(Integer servPrice) {
        this.servPrice = servPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanService that = (BeanService) o;
        return Objects.equals(servId, that.servId) &&
                Objects.equals(servName, that.servName) &&
                Objects.equals(servPrice, that.servPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(servId, servName, servPrice);
    }

    public Set<BeanAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<BeanAppointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return this.getServName();
    }
}
