package model;

import java.util.Arrays;
import java.util.Objects;

public class BeanPet {
    private Integer petId;
    private String petNikename;
    private String petAlias;
    private Integer petOwner;
    private byte[] petImg;
    public static final String[] tableTitles={"ID","昵称","别名","主人"};

    public String getCell(int col){
        if(col==0) return this.petId.toString();
        else if(col==1) return this.petNikename.toString();
        else if(col==2) return this.petAlias.toString();
        else if(col==3) return this.petOwner.toString();
        else return "";
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetNikename() {
        return petNikename;
    }

    public void setPetNikename(String petNikename) {
        this.petNikename = petNikename;
    }

    public String getPetAlias() {
        return petAlias;
    }

    public void setPetAlias(String petAlias) {
        this.petAlias = petAlias;
    }

    public Integer getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(Integer petOwner) {
        this.petOwner = petOwner;
    }

    public byte[] getPetImg() {
        return petImg;
    }

    public void setPetImg(byte[] petImg) {
        this.petImg = petImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanPet beanPet = (BeanPet) o;
        return Objects.equals(petId, beanPet.petId) &&
                Objects.equals(petNikename, beanPet.petNikename) &&
                Objects.equals(petAlias, beanPet.petAlias) &&
                Objects.equals(petOwner, beanPet.petOwner) &&
                Arrays.equals(petImg, beanPet.petImg);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(petId, petNikename, petAlias, petOwner);
        result = 31 * result + Arrays.hashCode(petImg);
        return result;
    }
}
