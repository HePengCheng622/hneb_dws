package com.hneb.dws.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Entity
@Table(name = "t_svc_order")
public class SvcOrderVO {
    private String cOrderId;
    private String cServiceId;
    private String cProdType;
    private Date tOrderTm;
    private Date tBuyTm;
    private Double nPrice;
    private int nNum;
    private String cSalerId;
    private String cBuyerId;
    private Double nFinalPrice;
    private String cPayMrk;
    private String cOrderSts;
    private String cServiceSts;
    private Integer nEva1;
    private Integer nEva2;
    private Integer nEva3;
    private String cEva;
    private String cBuyerMsg;
    private String cSalerMsg;
    private String cSchedId;
    private String cSchedMrk;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private String cRefundRsn;
    private Double nRefund;
    private Integer nDoneNum;

    @Id
    @Column(name = "C_ORDER_ID")
    public String getcOrderId() {
        return cOrderId;
    }

    public void setcOrderId(String cOrderId) {
        this.cOrderId = cOrderId;
    }

    @Basic
    @Column(name = "C_SERVICE_ID")
    public String getcServiceId() {
        return cServiceId;
    }

    public void setcServiceId(String cServiceId) {
        this.cServiceId = cServiceId;
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
    @Column(name = "T_ORDER_TM")
    public Date gettOrderTm() {
        return tOrderTm;
    }

    public void settOrderTm(Date tOrderTm) {
        this.tOrderTm = tOrderTm;
    }

    @Basic
    @Column(name = "T_BUY_TM")
    public Date gettBuyTm() {
        return tBuyTm;
    }

    public void settBuyTm(Date tBuyTm) {
        this.tBuyTm = tBuyTm;
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
    public int getnNum() {
        return nNum;
    }

    public void setnNum(int nNum) {
        this.nNum = nNum;
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
    @Column(name = "C_BUYER_ID")
    public String getcBuyerId() {
        return cBuyerId;
    }

    public void setcBuyerId(String cBuyerId) {
        this.cBuyerId = cBuyerId;
    }

    @Basic
    @Column(name = "N_FINAL_PRICE")
    public Double getnFinalPrice() {
        return nFinalPrice;
    }

    public void setnFinalPrice(Double nFinalPrice) {
        this.nFinalPrice = nFinalPrice;
    }

    @Basic
    @Column(name = "C_PAY_MRK")
    public String getcPayMrk() {
        return cPayMrk;
    }

    public void setcPayMrk(String cPayMrk) {
        this.cPayMrk = cPayMrk;
    }

    @Basic
    @Column(name = "C_ORDER_STS")
    public String getcOrderSts() {
        return cOrderSts;
    }

    public void setcOrderSts(String cOrderSts) {
        this.cOrderSts = cOrderSts;
    }

    @Basic
    @Column(name = "C_SERVICE_STS")
    public String getcServiceSts() {
        return cServiceSts;
    }

    public void setcServiceSts(String cServiceSts) {
        this.cServiceSts = cServiceSts;
    }

    @Basic
    @Column(name = "N_EVA1")
    public Integer getnEva1() {
        return nEva1;
    }

    public void setnEva1(Integer nEva1) {
        this.nEva1 = nEva1;
    }

    @Basic
    @Column(name = "N_EVA2")
    public Integer getnEva2() {
        return nEva2;
    }

    public void setnEva2(Integer nEva2) {
        this.nEva2 = nEva2;
    }

    @Basic
    @Column(name = "N_EVA3")
    public Integer getnEva3() {
        return nEva3;
    }

    public void setnEva3(Integer nEva3) {
        this.nEva3 = nEva3;
    }

    @Basic
    @Column(name = "C_EVA")
    public String getcEva() {
        return cEva;
    }

    public void setcEva(String cEva) {
        this.cEva = cEva;
    }

    @Basic
    @Column(name = "C_BUYER_MSG")
    public String getcBuyerMsg() {
        return cBuyerMsg;
    }

    public void setcBuyerMsg(String cBuyerMsg) {
        this.cBuyerMsg = cBuyerMsg;
    }

    @Basic
    @Column(name = "C_SALER_MSG")
    public String getcSalerMsg() {
        return cSalerMsg;
    }

    public void setcSalerMsg(String cSalerMsg) {
        this.cSalerMsg = cSalerMsg;
    }

    @Basic
    @Column(name = "C_SCHED_ID")
    public String getcSchedId() {
        return cSchedId;
    }

    public void setcSchedId(String cSchedId) {
        this.cSchedId = cSchedId;
    }

    @Basic
    @Column(name = "C_SCHED_MRK")
    public String getcSchedMrk() {
        return cSchedMrk;
    }

    public void setcSchedMrk(String cSchedMrk) {
        this.cSchedMrk = cSchedMrk;
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

    @Basic
    @Column(name = "C_REFUND_RSN")
    public String getcRefundRsn() {
        return cRefundRsn;
    }

    public void setcRefundRsn(String cRefundRsn) {
        this.cRefundRsn = cRefundRsn;
    }

    @Basic
    @Column(name = "N_REFUND")
    public Double getnRefund() {
        return nRefund;
    }

    public void setnRefund(Double nRefund) {
        this.nRefund = nRefund;
    }

    @Basic
    @Column(name = "N_DONE_NUM")
    public Integer getnDoneNum() {
        return nDoneNum;
    }

    public void setnDoneNum(Integer nDoneNum) {
        this.nDoneNum = nDoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SvcOrderVO that = (SvcOrderVO) o;

        if (nNum != that.nNum) return false;
        if (cOrderId != null ? !cOrderId.equals(that.cOrderId) : that.cOrderId != null) return false;
        if (cServiceId != null ? !cServiceId.equals(that.cServiceId) : that.cServiceId != null) return false;
        if (cProdType != null ? !cProdType.equals(that.cProdType) : that.cProdType != null) return false;
        if (tOrderTm != null ? !tOrderTm.equals(that.tOrderTm) : that.tOrderTm != null) return false;
        if (tBuyTm != null ? !tBuyTm.equals(that.tBuyTm) : that.tBuyTm != null) return false;
        if (nPrice != null ? !nPrice.equals(that.nPrice) : that.nPrice != null) return false;
        if (cSalerId != null ? !cSalerId.equals(that.cSalerId) : that.cSalerId != null) return false;
        if (cBuyerId != null ? !cBuyerId.equals(that.cBuyerId) : that.cBuyerId != null) return false;
        if (nFinalPrice != null ? !nFinalPrice.equals(that.nFinalPrice) : that.nFinalPrice != null) return false;
        if (cPayMrk != null ? !cPayMrk.equals(that.cPayMrk) : that.cPayMrk != null) return false;
        if (cOrderSts != null ? !cOrderSts.equals(that.cOrderSts) : that.cOrderSts != null) return false;
        if (cServiceSts != null ? !cServiceSts.equals(that.cServiceSts) : that.cServiceSts != null) return false;
        if (nEva1 != null ? !nEva1.equals(that.nEva1) : that.nEva1 != null) return false;
        if (nEva2 != null ? !nEva2.equals(that.nEva2) : that.nEva2 != null) return false;
        if (nEva3 != null ? !nEva3.equals(that.nEva3) : that.nEva3 != null) return false;
        if (cEva != null ? !cEva.equals(that.cEva) : that.cEva != null) return false;
        if (cBuyerMsg != null ? !cBuyerMsg.equals(that.cBuyerMsg) : that.cBuyerMsg != null) return false;
        if (cSalerMsg != null ? !cSalerMsg.equals(that.cSalerMsg) : that.cSalerMsg != null) return false;
        if (cSchedId != null ? !cSchedId.equals(that.cSchedId) : that.cSchedId != null) return false;
        if (cSchedMrk != null ? !cSchedMrk.equals(that.cSchedMrk) : that.cSchedMrk != null) return false;
        if (cCrtId != null ? !cCrtId.equals(that.cCrtId) : that.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(that.tCrtTm) : that.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(that.cUpdId) : that.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(that.tUpdTm) : that.tUpdTm != null) return false;
        if (cRefundRsn != null ? !cRefundRsn.equals(that.cRefundRsn) : that.cRefundRsn != null) return false;
        if (nRefund != null ? !nRefund.equals(that.nRefund) : that.nRefund != null) return false;
        if (nDoneNum != null ? !nDoneNum.equals(that.nDoneNum) : that.nDoneNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cOrderId != null ? cOrderId.hashCode() : 0;
        result = 31 * result + (cServiceId != null ? cServiceId.hashCode() : 0);
        result = 31 * result + (cProdType != null ? cProdType.hashCode() : 0);
        result = 31 * result + (tOrderTm != null ? tOrderTm.hashCode() : 0);
        result = 31 * result + (tBuyTm != null ? tBuyTm.hashCode() : 0);
        result = 31 * result + (nPrice != null ? nPrice.hashCode() : 0);
        result = 31 * result + nNum;
        result = 31 * result + (cSalerId != null ? cSalerId.hashCode() : 0);
        result = 31 * result + (cBuyerId != null ? cBuyerId.hashCode() : 0);
        result = 31 * result + (nFinalPrice != null ? nFinalPrice.hashCode() : 0);
        result = 31 * result + (cPayMrk != null ? cPayMrk.hashCode() : 0);
        result = 31 * result + (cOrderSts != null ? cOrderSts.hashCode() : 0);
        result = 31 * result + (cServiceSts != null ? cServiceSts.hashCode() : 0);
        result = 31 * result + (nEva1 != null ? nEva1.hashCode() : 0);
        result = 31 * result + (nEva2 != null ? nEva2.hashCode() : 0);
        result = 31 * result + (nEva3 != null ? nEva3.hashCode() : 0);
        result = 31 * result + (cEva != null ? cEva.hashCode() : 0);
        result = 31 * result + (cBuyerMsg != null ? cBuyerMsg.hashCode() : 0);
        result = 31 * result + (cSalerMsg != null ? cSalerMsg.hashCode() : 0);
        result = 31 * result + (cSchedId != null ? cSchedId.hashCode() : 0);
        result = 31 * result + (cSchedMrk != null ? cSchedMrk.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        result = 31 * result + (cRefundRsn != null ? cRefundRsn.hashCode() : 0);
        result = 31 * result + (nRefund != null ? nRefund.hashCode() : 0);
        result = 31 * result + (nDoneNum != null ? nDoneNum.hashCode() : 0);
        return result;
    }
}
