package com.hneb.dws.vo;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/12/12.
 */
@Entity
@Table(name = "t_nutrient")
public class NutrientVO {
    private Integer cPkId;
    private String cItem;
    private Float nAge;
    private String cSex;
    private String cType;
    private Float nVal;

    @Id
    @Column(name = "C_PK_ID")
    public Integer getcPkId() {
        return cPkId;
    }

    public void setcPkId(Integer cPkId) {
        this.cPkId = cPkId;
    }

    @Basic
    @Column(name = "C_ITEM")
    public String getcItem() {
        return cItem;
    }

    public void setcItem(String cItem) {
        this.cItem = cItem;
    }

    @Basic
    @Column(name = "N_AGE")
    public Float getnAge() {
        return nAge;
    }

    public void setnAge(Float nAge) {
        this.nAge = nAge;
    }

    @Basic
    @Column(name = "C_SEX")
    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex;
    }

    @Basic
    @Column(name = "C_TYPE")
    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    @Basic
    @Column(name = "N_VAL")
    public Float getnVal() {
        return nVal;
    }

    public void setnVal(Float nVal) {
        this.nVal = nVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NutrientVO that = (NutrientVO) o;

        if (cPkId != null ? !cPkId.equals(that.cPkId) : that.cPkId != null) return false;
        if (cItem != null ? !cItem.equals(that.cItem) : that.cItem != null) return false;
        if (nAge != null ? !nAge.equals(that.nAge) : that.nAge != null) return false;
        if (cSex != null ? !cSex.equals(that.cSex) : that.cSex != null) return false;
        if (cType != null ? !cType.equals(that.cType) : that.cType != null) return false;
        if (nVal != null ? !nVal.equals(that.nVal) : that.nVal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cItem != null ? cItem.hashCode() : 0);
        result = 31 * result + (nAge != null ? nAge.hashCode() : 0);
        result = 31 * result + (cSex != null ? cSex.hashCode() : 0);
        result = 31 * result + (cType != null ? cType.hashCode() : 0);
        result = 31 * result + (nVal != null ? nVal.hashCode() : 0);
        return result;
    }
}
