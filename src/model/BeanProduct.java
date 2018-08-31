package model;

import java.util.Objects;

public class BeanProduct {
    private Integer prodId;
    private Integer icateD;
    private String prodName;
    private Integer prodCate;
    private String prodBrand;
    private Integer prodPrice;
    private String prodBarcode;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getIcateD() {
        return icateD;
    }

    public void setIcateD(Integer icateD) {
        this.icateD = icateD;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdCate() {
        return prodCate;
    }

    public void setProdCate(Integer prodCate) {
        this.prodCate = prodCate;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdBarcode() {
        return prodBarcode;
    }

    public void setProdBarcode(String prodBarcode) {
        this.prodBarcode = prodBarcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanProduct that = (BeanProduct) o;
        return Objects.equals(prodId, that.prodId) &&
                Objects.equals(icateD, that.icateD) &&
                Objects.equals(prodName, that.prodName) &&
                Objects.equals(prodCate, that.prodCate) &&
                Objects.equals(prodBrand, that.prodBrand) &&
                Objects.equals(prodPrice, that.prodPrice) &&
                Objects.equals(prodBarcode, that.prodBarcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(prodId, icateD, prodName, prodCate, prodBrand, prodPrice, prodBarcode);
    }
}
