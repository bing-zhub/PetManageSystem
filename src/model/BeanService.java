package model;

import java.util.Objects;

public class BeanService {
    private Integer servId;
    private String servName;
    private Integer servPrice;

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
}
