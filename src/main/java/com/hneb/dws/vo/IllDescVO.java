package com.hneb.dws.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Entity
@Table(name = "t_ill_desc")
public class IllDescVO {
    private String cOrderId;
    private String cBuyerId;
    private String cChildId;
    private String cIllNme;
    private String cIllTm;
    private String cIllDesc;
    private String cImg1;
    private String cImg2;
    private String cImg3;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

    @Id
    @Column(name = "C_ORDER_ID")
    public String getcOrderId() {
        return cOrderId;
    }

    public void setcOrderId(String cOrderId) {
        this.cOrderId = cOrderId;
    }

    @Basic
    @Column(name = "C_BUYER_ID")
    public String getcBuyerId() {
        return cBuyerId;
    }

    public void setcBuyerId(String cBuyerId) {
        this.cBuyerId = cBuyerId;
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
    @Column(name = "C_ILL_NME")
    public String getcIllNme() {
        return cIllNme;
    }

    public void setcIllNme(String cIllNme) {
        this.cIllNme = cIllNme;
    }

    @Basic
    @Column(name = "C_ILL_TM")
    public String getcIllTm() {
        return cIllTm;
    }

    public void setcIllTm(String cIllTm) {
        this.cIllTm = cIllTm;
    }

    @Basic
    @Column(name = "C_ILL_DESC")
    public String getcIllDesc() {
        return cIllDesc;
    }

    public void setcIllDesc(String cIllDesc) {
        this.cIllDesc = cIllDesc;
    }

    @Basic
    @Column(name = "C_IMG1")
    public String getcImg1() {
        return cImg1;
    }

    public void setcImg1(String cImg1) {
        this.cImg1 = cImg1;
    }

    @Basic
    @Column(name = "C_IMG2")
    public String getcImg2() {
        return cImg2;
    }

    public void setcImg2(String cImg2) {
        this.cImg2 = cImg2;
    }

    @Basic
    @Column(name = "C_IMG3")
    public String getcImg3() {
        return cImg3;
    }

    public void setcImg3(String cImg3) {
        this.cImg3 = cImg3;
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

        IllDescVO illDescVO = (IllDescVO) o;

        if (cOrderId != null ? !cOrderId.equals(illDescVO.cOrderId) : illDescVO.cOrderId != null) return false;
        if (cBuyerId != null ? !cBuyerId.equals(illDescVO.cBuyerId) : illDescVO.cBuyerId != null) return false;
        if (cChildId != null ? !cChildId.equals(illDescVO.cChildId) : illDescVO.cChildId != null) return false;
        if (cIllNme != null ? !cIllNme.equals(illDescVO.cIllNme) : illDescVO.cIllNme != null) return false;
        if (cIllTm != null ? !cIllTm.equals(illDescVO.cIllTm) : illDescVO.cIllTm != null) return false;
        if (cIllDesc != null ? !cIllDesc.equals(illDescVO.cIllDesc) : illDescVO.cIllDesc != null) return false;
        if (cImg1 != null ? !cImg1.equals(illDescVO.cImg1) : illDescVO.cImg1 != null) return false;
        if (cImg2 != null ? !cImg2.equals(illDescVO.cImg2) : illDescVO.cImg2 != null) return false;
        if (cImg3 != null ? !cImg3.equals(illDescVO.cImg3) : illDescVO.cImg3 != null) return false;
        if (cCrtId != null ? !cCrtId.equals(illDescVO.cCrtId) : illDescVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(illDescVO.tCrtTm) : illDescVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(illDescVO.cUpdId) : illDescVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(illDescVO.tUpdTm) : illDescVO.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cOrderId != null ? cOrderId.hashCode() : 0;
        result = 31 * result + (cBuyerId != null ? cBuyerId.hashCode() : 0);
        result = 31 * result + (cChildId != null ? cChildId.hashCode() : 0);
        result = 31 * result + (cIllNme != null ? cIllNme.hashCode() : 0);
        result = 31 * result + (cIllTm != null ? cIllTm.hashCode() : 0);
        result = 31 * result + (cIllDesc != null ? cIllDesc.hashCode() : 0);
        result = 31 * result + (cImg1 != null ? cImg1.hashCode() : 0);
        result = 31 * result + (cImg2 != null ? cImg2.hashCode() : 0);
        result = 31 * result + (cImg3 != null ? cImg3.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
