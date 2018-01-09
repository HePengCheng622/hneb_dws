package com.hneb.dws.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/4.
 */
@Entity
@Table(name = "t_taboo")
public class TabooVO {
    private String cPkId;
    private String cTaboo;
    private String cFoods;
    private String cUserId;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private String cEffMrk;

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
    @Column(name = "C_TABOO")
    public String getcTaboo() {
        return cTaboo;
    }

    public void setcTaboo(String cTaboo) {
        this.cTaboo = cTaboo;
    }

    @Basic
    @Column(name = "C_FOODS")
    public String getcFoods() {
        return cFoods;
    }

    public void setcFoods(String cFoods) {
        this.cFoods = cFoods;
    }

    @Basic
    @Column(name = "C_USER_ID")
    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId;
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

        TabooVO tabooVO = (TabooVO) o;

        if (cPkId != null ? !cPkId.equals(tabooVO.cPkId) : tabooVO.cPkId != null) return false;
        if (cTaboo != null ? !cTaboo.equals(tabooVO.cTaboo) : tabooVO.cTaboo != null) return false;
        if (cFoods != null ? !cFoods.equals(tabooVO.cFoods) : tabooVO.cFoods != null) return false;
        if (cUserId != null ? !cUserId.equals(tabooVO.cUserId) : tabooVO.cUserId != null) return false;
        if (cCrtId != null ? !cCrtId.equals(tabooVO.cCrtId) : tabooVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(tabooVO.tCrtTm) : tabooVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(tabooVO.cUpdId) : tabooVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(tabooVO.tUpdTm) : tabooVO.tUpdTm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cTaboo != null ? cTaboo.hashCode() : 0);
        result = 31 * result + (cFoods != null ? cFoods.hashCode() : 0);
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "C_EFF_MRK")
    public String getcEffMrk() {
        return cEffMrk;
    }

    public void setcEffMrk(String cEffMrk) {
        this.cEffMrk = cEffMrk;
    }
}
