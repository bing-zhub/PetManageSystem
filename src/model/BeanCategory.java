package model;

import java.util.Objects;

public class BeanCategory {
    private Integer cateId;
    private String cateName;
    private String cateDetail;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateDetail() {
        return cateDetail;
    }

    public void setCateDetail(String cateDetail) {
        this.cateDetail = cateDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanCategory that = (BeanCategory) o;
        return Objects.equals(cateId, that.cateId) &&
                Objects.equals(cateName, that.cateName) &&
                Objects.equals(cateDetail, that.cateDetail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cateId, cateName, cateDetail);
    }
}
