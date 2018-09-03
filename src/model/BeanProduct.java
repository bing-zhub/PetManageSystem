package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BeanProduct {
    private Integer prodId;
    private String prodName;
    private Integer cateId;
    private String prodBrand;
    private Integer prodPrice;
    private String prodBarcode;
    public static final String[] tableTitles={"ID","名称","类别","品牌","价格","条形码"};

    public String getCell(int col){
        if(col==0) return this.prodId.toString();
        else if(col==1) return this.prodName.toString();
        else if(col==2) return this.cateId.toString();
        else if(col==3) return this.prodBrand.toString();
        else if(col==4) return this.prodPrice.toString();
        else if(col==5) return this.prodBarcode.toString();
        else return "";
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer prodCate) {
        this.cateId = prodCate;
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
                Objects.equals(prodName, that.prodName) &&
                Objects.equals(cateId, that.cateId) &&
                Objects.equals(prodBrand, that.prodBrand) &&
                Objects.equals(prodPrice, that.prodPrice) &&
                Objects.equals(prodBarcode, that.prodBarcode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(prodId, prodName, cateId, prodBrand, prodPrice, prodBarcode);
    }

    @Override
    public String toString() {
        return getProdName();
    }


}
