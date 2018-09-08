package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeanService {
    private Integer servId;
    private String servName;
    private Integer servPrice;
    private BeanCategory category;

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


    @Override
    public String toString() {
        return this.getServName();
    }

    public BeanCategory getCategory() {
        return category;
    }

    public void setCategory(BeanCategory category) {
        this.category = category;
    }
}
