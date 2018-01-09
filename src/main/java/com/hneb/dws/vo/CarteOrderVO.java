package com.hneb.dws.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/25.
 */
@Entity
@Table(name = "t_carte_order")
public class CarteOrderVO {
    private String cPkId;
    private String cOrderId;
    private String cCateringId;
    private Date tEatTm;
    private String cMealMrk;
    private String cParentId;
    private String cChildId;
    private String cYysId;
    private String cDptId;
    private String cAddrId;
    private String cCarteId;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

    @Id
    @Column(name = "C_PK_ID")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
    }

    @Basic
    @Column(name = "C_ORDER_ID")
    public String getcOrderId() {
        return cOrderId;
    }

    public void setcOrderId(String cOrderId) {
        this.cOrderId = cOrderId;
    }

    @Basic
    @Column(name = "C_CATERING_ID")
    public String getcCateringId() {
        return cCateringId;
    }

    public void setcCateringId(String cCateringId) {
        this.cCateringId = cCateringId;
    }

    @Basic
    @Column(name = "T_EAT_TM")
    public Date gettEatTm() {
        return tEatTm;
    }

    public void settEatTm(Date tEatTm) {
        this.tEatTm = tEatTm;
    }

    @Basic
    @Column(name = "C_MEAL_MRK")
    public String getcMealMrk() {
        return cMealMrk;
    }

    public void setcMealMrk(String cMealMrk) {
        this.cMealMrk = cMealMrk;
    }

    @Basic
    @Column(name = "C_PARENT_ID")
    public String getcParentId() {
        return cParentId;
    }

    public void setcParentId(String cParentId) {
        this.cParentId = cParentId;
    }

    @Basic
    @Column(name = "C_CHILD_ID")
    public String getcChildId() {
        return cChildId;
    }

    public void setcChildId(String cChildId) {
        this.cChildId = cChildId;
    }

    @Basic
    @Column(name = "C_YYS_ID")
    public String getcYysId() {
        return cYysId;
    }

    public void setcYysId(String cYysId) {
        this.cYysId = cYysId;
    }

    @Basic
    @Column(name = "C_DPT_ID")
    public String getcDptId() {
        return cDptId;
    }

    public void setcDptId(String cDptId) {
        this.cDptId = cDptId;
    }

    @Basic
    @Column(name = "C_ADDR_ID")
    public String getcAddrId() {
        return cAddrId;
    }

    public void setcAddrId(String cAddrId) {
        this.cAddrId = cAddrId;
    }

    @Basic
    @Column(name = "C_CARTE_ID")
    public String getcCarteId() {
        return cCarteId;
    }

    public void setcCarteId(String cCarteId) {
        this.cCarteId = cCarteId;
    }

    @Basic
    @Column(name = "C_CRT_ID")
    public String getcCrtId() {
        return cCrtId;
    }

    public void setcCrtId(String cCrtId) {
        this.cCrtId = cCrtId;
    }

    @Basic
    @Column(name = "T_CRT_TM")
    public Date gettCrtTm() {
        return tCrtTm;
    }

    public void settCrtTm(Date tCrtTm) {
        this.tCrtTm = tCrtTm;
    }

    @Basic
    @Column(name = "C_UPD_ID")
    public String getcUpdId() {
        return cUpdId;
    }

    public void setcUpdId(String cUpdId) {
        this.cUpdId = cUpdId;
    }

    @Basic
    @Column(name = "T_UPD_TM")
    public Date gettUpdTm() {
        return tUpdTm;
    }

    public void settUpdTm(Date tUpdTm) {
        this.tUpdTm = tUpdTm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarteOrderVO that = (CarteOrderVO) o;

        if (cPkId != null ? !cPkId.equals(that.cPkId) : that.cPkId != null) return false;
        if (cOrderId != null ? !cOrderId.equals(that.cOrderId) : that.cOrderId != null) return false;
        if (cCateringId != null ? !cCateringId.equals(that.cCateringId) : that.cCateringId != null) return false;
        if (tEatTm != null ? !tEatTm.equals(that.tEatTm) : that.tEatTm != null) return false;
        if (cMealMrk != null ? !cMealMrk.equals(that.cMealMrk) : that.cMealMrk != null) return false;
        if (cParentId != null ? !cParentId.equals(that.cParentId) : that.cParentId != null) return false;
        if (cChildId != null ? !cChildId.equals(that.cChildId) : that.cChildId != null) return false;
        if (cYysId != null ? !cYysId.equals(that.cYysId) : that.cYysId != null) return false;
        if (cDptId != null ? !cDptId.equals(that.cDptId) : that.cDptId != null) return false;
        if (cAddrId != null ? !cAddrId.equals(that.cAddrId) : that.cAddrId != null) return false;
        if (cCarteId != null ? !cCarteId.equals(that.cCarteId) : that.cCarteId != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cOrderId != null ? cOrderId.hashCode() : 0);
        result = 31 * result + (cCateringId != null ? cCateringId.hashCode() : 0);
        result = 31 * result + (tEatTm != null ? tEatTm.hashCode() : 0);
        result = 31 * result + (cMealMrk != null ? cMealMrk.hashCode() : 0);
        result = 31 * result + (cParentId != null ? cParentId.hashCode() : 0);
        result = 31 * result + (cChildId != null ? cChildId.hashCode() : 0);
        result = 31 * result + (cYysId != null ? cYysId.hashCode() : 0);
        result = 31 * result + (cDptId != null ? cDptId.hashCode() : 0);
        result = 31 * result + (cAddrId != null ? cAddrId.hashCode() : 0);
        result = 31 * result + (cCarteId != null ? cCarteId.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
