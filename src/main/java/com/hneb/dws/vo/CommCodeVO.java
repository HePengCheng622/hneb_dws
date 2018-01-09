package com.hneb.dws.vo;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Entity
@Table(name = "t_comm_code")
public class CommCodeVO {
    private String cCode;
    private String cText;
    private String cParentCode;
    private String cUserId;
    private String cEffMrk;

    @Id
    @Column(name = "C_CODE")
    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    @Basic
    @Column(name = "C_TEXT")
    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    @Basic
    @Column(name = "C_PARENT_CODE")
    public String getcParentCode() {
        return cParentCode;
    }

    public void setcParentCode(String cParentCode) {
        this.cParentCode = cParentCode;
    }

    @Basic
    @Column(name = "C_USER_ID")
    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommCodeVO that = (CommCodeVO) o;

        if (cCode != null ? !cCode.equals(that.cCode) : that.cCode != null) return false;
        if (cText != null ? !cText.equals(that.cText) : that.cText != null) return false;
        if (cParentCode != null ? !cParentCode.equals(that.cParentCode) : that.cParentCode != null) return false;
        if (cUserId != null ? !cUserId.equals(that.cUserId) : that.cUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cCode != null ? cCode.hashCode() : 0;
        result = 31 * result + (cText != null ? cText.hashCode() : 0);
        result = 31 * result + (cParentCode != null ? cParentCode.hashCode() : 0);
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
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
