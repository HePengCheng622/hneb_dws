package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/10.
 */
@Entity
@Table(name = "t_svc_price")
public class SvcPriceVO {
    private String cPkId;
    private String cSalerId;
    private String cProdType;
    private Double nPrice;
    private Integer nNum;
    private String cMemo;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;

    @Id
    @Column(name = "C_PK_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
    }

    @Basic
    @Column(name = "C_SALER_ID")
    public String getcSalerId() {
        return cSalerId;
    }

    public void setcSalerId(String cSalerId) {
        this.cSalerId = cSalerId;
    }

    @Basic
    @Column(name = "C_PROD_TYPE")
    public String getcProdType() {
        return cProdType;
    }

    public void setcProdType(String cProdType) {
        this.cProdType = cProdType;
    }

    @Basic
    @Column(name = "N_PRICE")
    public Double getnPrice() {
        return nPrice;
    }

    public void setnPrice(Double nPrice) {
        this.nPrice = nPrice;
    }

    @Basic
    @Column(name = "N_NUM")
    public Integer getnNum() {
        return nNum;
    }

    public void setnNum(Integer nNum) {
        this.nNum = nNum;
    }

    @Basic
    @Column(name = "C_MEMO")
    public String getcMemo() {
        return cMemo;
    }

    public void setcMemo(String cMemo) {
        this.cMemo = cMemo;
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

        SvcPriceVO that = (SvcPriceVO) o;

        if (cPkId != null ? !cPkId.equals(that.cPkId) : that.cPkId != null) return false;
        if (cSalerId != null ? !cSalerId.equals(that.cSalerId) : that.cSalerId != null) return false;
        if (cProdType != null ? !cProdType.equals(that.cProdType) : that.cProdType != null) return false;
        if (nPrice != null ? !nPrice.equals(that.nPrice) : that.nPrice != null) return false;
        if (nNum != null ? !nNum.equals(that.nNum) : that.nNum != null) return false;
        if (cMemo != null ? !cMemo.equals(that.cMemo) : that.cMemo != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cSalerId != null ? cSalerId.hashCode() : 0);
        result = 31 * result + (cProdType != null ? cProdType.hashCode() : 0);
        result = 31 * result + (nPrice != null ? nPrice.hashCode() : 0);
        result = 31 * result + (nNum != null ? nNum.hashCode() : 0);
        result = 31 * result + (cMemo != null ? cMemo.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }
}
