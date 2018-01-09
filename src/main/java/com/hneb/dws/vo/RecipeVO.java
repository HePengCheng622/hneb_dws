package com.hneb.dws.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/4.
 */
@Entity
@Table(name = "t_recipe")
public class RecipeVO {
    private String cPkId;
    private String cUserId;
    private String cName;
    private String cTypes;
    private String cTags;
    private String cSeasoning;
    private String cFeatures;
    private String cMatter;
    private String cPractice;
    private String cCrtId;
    private Date tCrtTm;
    private String cUpdId;
    private Date tUpdTm;
    private Double nWater;
    private Double nEnergy1;
    private Double nEnergy2;
    private Double nProtein;
    private Double nFat;
    private Double nCho;
    private Double nDietaryFiber;
    private Double nCholesterol;
    private Double nAsh;
    private Double nVa;
    private Double nCarotene;
    private Double nRetinol;
    private Double nThiamin;
    private Double nRiboflavin;
    private Double nNiacin;
    private Double nVc;
    private Double nVeTotal;
    private Double nCa;
    private Double nP;
    private Double nK;
    private Double nNa;
    private Double nMg;
    private Double nFe;
    private Double nZn;
    private Double nSe;
    private Double nCu;
    private Double nMn;
    private String cLatestMrk;
    private String cOrgId;
    private Integer nWeight;
    private String cFoodDesc;

    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    @Column(name = "C_PK_ID")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
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
    @Column(name = "C_NAME")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "C_TYPES")
    public String getcTypes() {
        return cTypes;
    }

    public void setcTypes(String cTypes) {
        this.cTypes = cTypes;
    }

    @Basic
    @Column(name = "C_TAGS")
    public String getcTags() {
        return cTags;
    }

    public void setcTags(String cTags) {
        this.cTags = cTags;
    }

    @Basic
    @Column(name = "C_SEASONING")
    public String getcSeasoning() {
        return cSeasoning;
    }

    public void setcSeasoning(String cSeasoning) {
        this.cSeasoning = cSeasoning;
    }

    @Basic
    @Column(name = "C_FEATURES")
    public String getcFeatures() {
        return cFeatures;
    }

    public void setcFeatures(String cFeatures) {
        this.cFeatures = cFeatures;
    }

    @Basic
    @Column(name = "C_MATTER")
    public String getcMatter() {
        return cMatter;
    }

    public void setcMatter(String cMatter) {
        this.cMatter = cMatter;
    }

    @Basic
    @Column(name = "C_PRACTICE")
    public String getcPractice() {
        return cPractice;
    }

    public void setcPractice(String cPractice) {
        this.cPractice = cPractice;
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
    @Column(name = "N_WATER")
    public Double getnWater() {
        return nWater;
    }

    public void setnWater(Double nWater) {
        this.nWater = nWater;
    }

    @Basic
    @Column(name = "N_ENERGY_1")
    public Double getnEnergy1() {
        return nEnergy1;
    }

    public void setnEnergy1(Double nEnergy1) {
        this.nEnergy1 = nEnergy1;
    }

    @Basic
    @Column(name = "N_ENERGY_2")
    public Double getnEnergy2() {
        return nEnergy2;
    }

    public void setnEnergy2(Double nEnergy2) {
        this.nEnergy2 = nEnergy2;
    }

    @Basic
    @Column(name = "N_PROTEIN")
    public Double getnProtein() {
        return nProtein;
    }

    public void setnProtein(Double nProtein) {
        this.nProtein = nProtein;
    }

    @Basic
    @Column(name = "N_FAT")
    public Double getnFat() {
        return nFat;
    }

    public void setnFat(Double nFat) {
        this.nFat = nFat;
    }

    @Basic
    @Column(name = "N_CHO")
    public Double getnCho() {
        return nCho;
    }

    public void setnCho(Double nCho) {
        this.nCho = nCho;
    }

    @Basic
    @Column(name = "N_DIETARY_FIBER")
    public Double getnDietaryFiber() {
        return nDietaryFiber;
    }

    public void setnDietaryFiber(Double nDietaryFiber) {
        this.nDietaryFiber = nDietaryFiber;
    }

    @Basic
    @Column(name = "N_CHOLESTEROL")
    public Double getnCholesterol() {
        return nCholesterol;
    }

    public void setnCholesterol(Double nCholesterol) {
        this.nCholesterol = nCholesterol;
    }

    @Basic
    @Column(name = "N_ASH")
    public Double getnAsh() {
        return nAsh;
    }

    public void setnAsh(Double nAsh) {
        this.nAsh = nAsh;
    }

    @Basic
    @Column(name = "N_VA")
    public Double getnVa() {
        return nVa;
    }

    public void setnVa(Double nVa) {
        this.nVa = nVa;
    }

    @Basic
    @Column(name = "N_CAROTENE")
    public Double getnCarotene() {
        return nCarotene;
    }

    public void setnCarotene(Double nCarotene) {
        this.nCarotene = nCarotene;
    }

    @Basic
    @Column(name = "N_RETINOL")
    public Double getnRetinol() {
        return nRetinol;
    }

    public void setnRetinol(Double nRetinol) {
        this.nRetinol = nRetinol;
    }

    @Basic
    @Column(name = "N_THIAMIN")
    public Double getnThiamin() {
        return nThiamin;
    }

    public void setnThiamin(Double nThiamin) {
        this.nThiamin = nThiamin;
    }

    @Basic
    @Column(name = "N_RIBOFLAVIN")
    public Double getnRiboflavin() {
        return nRiboflavin;
    }

    public void setnRiboflavin(Double nRiboflavin) {
        this.nRiboflavin = nRiboflavin;
    }

    @Basic
    @Column(name = "N_NIACIN")
    public Double getnNiacin() {
        return nNiacin;
    }

    public void setnNiacin(Double nNiacin) {
        this.nNiacin = nNiacin;
    }

    @Basic
    @Column(name = "N_VC")
    public Double getnVc() {
        return nVc;
    }

    public void setnVc(Double nVc) {
        this.nVc = nVc;
    }

    @Basic
    @Column(name = "N_VE_TOTAL")
    public Double getnVeTotal() {
        return nVeTotal;
    }

    public void setnVeTotal(Double nVeTotal) {
        this.nVeTotal = nVeTotal;
    }

    @Basic
    @Column(name = "N_CA")
    public Double getnCa() {
        return nCa;
    }

    public void setnCa(Double nCa) {
        this.nCa = nCa;
    }

    @Basic
    @Column(name = "N_P")
    public Double getnP() {
        return nP;
    }

    public void setnP(Double nP) {
        this.nP = nP;
    }

    @Basic
    @Column(name = "N_K")
    public Double getnK() {
        return nK;
    }

    public void setnK(Double nK) {
        this.nK = nK;
    }

    @Basic
    @Column(name = "N_NA")
    public Double getnNa() {
        return nNa;
    }

    public void setnNa(Double nNa) {
        this.nNa = nNa;
    }

    @Basic
    @Column(name = "N_MG")
    public Double getnMg() {
        return nMg;
    }

    public void setnMg(Double nMg) {
        this.nMg = nMg;
    }

    @Basic
    @Column(name = "N_FE")
    public Double getnFe() {
        return nFe;
    }

    public void setnFe(Double nFe) {
        this.nFe = nFe;
    }

    @Basic
    @Column(name = "N_ZN")
    public Double getnZn() {
        return nZn;
    }

    public void setnZn(Double nZn) {
        this.nZn = nZn;
    }

    @Basic
    @Column(name = "N_SE")
    public Double getnSe() {
        return nSe;
    }

    public void setnSe(Double nSe) {
        this.nSe = nSe;
    }

    @Basic
    @Column(name = "N_CU")
    public Double getnCu() {
        return nCu;
    }

    public void setnCu(Double nCu) {
        this.nCu = nCu;
    }

    @Basic
    @Column(name = "N_MN")
    public Double getnMn() {
        return nMn;
    }

    public void setnMn(Double nMn) {
        this.nMn = nMn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeVO recipeVO = (RecipeVO) o;

        if (cPkId != null ? !cPkId.equals(recipeVO.cPkId) : recipeVO.cPkId != null) return false;
        if (cUserId != null ? !cUserId.equals(recipeVO.cUserId) : recipeVO.cUserId != null) return false;
        if (cName != null ? !cName.equals(recipeVO.cName) : recipeVO.cName != null) return false;
        if (cTypes != null ? !cTypes.equals(recipeVO.cTypes) : recipeVO.cTypes != null) return false;
        if (cTags != null ? !cTags.equals(recipeVO.cTags) : recipeVO.cTags != null) return false;
        if (cSeasoning != null ? !cSeasoning.equals(recipeVO.cSeasoning) : recipeVO.cSeasoning != null) return false;
        if (cFeatures != null ? !cFeatures.equals(recipeVO.cFeatures) : recipeVO.cFeatures != null) return false;
        if (cMatter != null ? !cMatter.equals(recipeVO.cMatter) : recipeVO.cMatter != null) return false;
        if (cPractice != null ? !cPractice.equals(recipeVO.cPractice) : recipeVO.cPractice != null) return false;
        if (cCrtId != null ? !cCrtId.equals(recipeVO.cCrtId) : recipeVO.cCrtId != null) return false;
        if (tCrtTm != null ? !tCrtTm.equals(recipeVO.tCrtTm) : recipeVO.tCrtTm != null) return false;
        if (cUpdId != null ? !cUpdId.equals(recipeVO.cUpdId) : recipeVO.cUpdId != null) return false;
        if (tUpdTm != null ? !tUpdTm.equals(recipeVO.tUpdTm) : recipeVO.tUpdTm != null) return false;
        if (nWater != null ? !nWater.equals(recipeVO.nWater) : recipeVO.nWater != null) return false;
        if (nEnergy1 != null ? !nEnergy1.equals(recipeVO.nEnergy1) : recipeVO.nEnergy1 != null) return false;
        if (nEnergy2 != null ? !nEnergy2.equals(recipeVO.nEnergy2) : recipeVO.nEnergy2 != null) return false;
        if (nProtein != null ? !nProtein.equals(recipeVO.nProtein) : recipeVO.nProtein != null) return false;
        if (nFat != null ? !nFat.equals(recipeVO.nFat) : recipeVO.nFat != null) return false;
        if (nCho != null ? !nCho.equals(recipeVO.nCho) : recipeVO.nCho != null) return false;
        if (nDietaryFiber != null ? !nDietaryFiber.equals(recipeVO.nDietaryFiber) : recipeVO.nDietaryFiber != null)
            return false;
        if (nCholesterol != null ? !nCholesterol.equals(recipeVO.nCholesterol) : recipeVO.nCholesterol != null)
            return false;
        if (nAsh != null ? !nAsh.equals(recipeVO.nAsh) : recipeVO.nAsh != null) return false;
        if (nVa != null ? !nVa.equals(recipeVO.nVa) : recipeVO.nVa != null) return false;
        if (nCarotene != null ? !nCarotene.equals(recipeVO.nCarotene) : recipeVO.nCarotene != null) return false;
        if (nRetinol != null ? !nRetinol.equals(recipeVO.nRetinol) : recipeVO.nRetinol != null) return false;
        if (nThiamin != null ? !nThiamin.equals(recipeVO.nThiamin) : recipeVO.nThiamin != null) return false;
        if (nRiboflavin != null ? !nRiboflavin.equals(recipeVO.nRiboflavin) : recipeVO.nRiboflavin != null)
            return false;
        if (nNiacin != null ? !nNiacin.equals(recipeVO.nNiacin) : recipeVO.nNiacin != null) return false;
        if (nVc != null ? !nVc.equals(recipeVO.nVc) : recipeVO.nVc != null) return false;
        if (nVeTotal != null ? !nVeTotal.equals(recipeVO.nVeTotal) : recipeVO.nVeTotal != null) return false;
        if (nCa != null ? !nCa.equals(recipeVO.nCa) : recipeVO.nCa != null) return false;
        if (nP != null ? !nP.equals(recipeVO.nP) : recipeVO.nP != null) return false;
        if (nK != null ? !nK.equals(recipeVO.nK) : recipeVO.nK != null) return false;
        if (nNa != null ? !nNa.equals(recipeVO.nNa) : recipeVO.nNa != null) return false;
        if (nMg != null ? !nMg.equals(recipeVO.nMg) : recipeVO.nMg != null) return false;
        if (nFe != null ? !nFe.equals(recipeVO.nFe) : recipeVO.nFe != null) return false;
        if (nZn != null ? !nZn.equals(recipeVO.nZn) : recipeVO.nZn != null) return false;
        if (nSe != null ? !nSe.equals(recipeVO.nSe) : recipeVO.nSe != null) return false;
        if (nCu != null ? !nCu.equals(recipeVO.nCu) : recipeVO.nCu != null) return false;
        if (nMn != null ? !nMn.equals(recipeVO.nMn) : recipeVO.nMn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cUserId != null ? cUserId.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cTypes != null ? cTypes.hashCode() : 0);
        result = 31 * result + (cTags != null ? cTags.hashCode() : 0);
        result = 31 * result + (cSeasoning != null ? cSeasoning.hashCode() : 0);
        result = 31 * result + (cFeatures != null ? cFeatures.hashCode() : 0);
        result = 31 * result + (cMatter != null ? cMatter.hashCode() : 0);
        result = 31 * result + (cPractice != null ? cPractice.hashCode() : 0);
        result = 31 * result + (cCrtId != null ? cCrtId.hashCode() : 0);
        result = 31 * result + (tCrtTm != null ? tCrtTm.hashCode() : 0);
        result = 31 * result + (cUpdId != null ? cUpdId.hashCode() : 0);
        result = 31 * result + (tUpdTm != null ? tUpdTm.hashCode() : 0);
        result = 31 * result + (nWater != null ? nWater.hashCode() : 0);
        result = 31 * result + (nEnergy1 != null ? nEnergy1.hashCode() : 0);
        result = 31 * result + (nEnergy2 != null ? nEnergy2.hashCode() : 0);
        result = 31 * result + (nProtein != null ? nProtein.hashCode() : 0);
        result = 31 * result + (nFat != null ? nFat.hashCode() : 0);
        result = 31 * result + (nCho != null ? nCho.hashCode() : 0);
        result = 31 * result + (nDietaryFiber != null ? nDietaryFiber.hashCode() : 0);
        result = 31 * result + (nCholesterol != null ? nCholesterol.hashCode() : 0);
        result = 31 * result + (nAsh != null ? nAsh.hashCode() : 0);
        result = 31 * result + (nVa != null ? nVa.hashCode() : 0);
        result = 31 * result + (nCarotene != null ? nCarotene.hashCode() : 0);
        result = 31 * result + (nRetinol != null ? nRetinol.hashCode() : 0);
        result = 31 * result + (nThiamin != null ? nThiamin.hashCode() : 0);
        result = 31 * result + (nRiboflavin != null ? nRiboflavin.hashCode() : 0);
        result = 31 * result + (nNiacin != null ? nNiacin.hashCode() : 0);
        result = 31 * result + (nVc != null ? nVc.hashCode() : 0);
        result = 31 * result + (nVeTotal != null ? nVeTotal.hashCode() : 0);
        result = 31 * result + (nCa != null ? nCa.hashCode() : 0);
        result = 31 * result + (nP != null ? nP.hashCode() : 0);
        result = 31 * result + (nK != null ? nK.hashCode() : 0);
        result = 31 * result + (nNa != null ? nNa.hashCode() : 0);
        result = 31 * result + (nMg != null ? nMg.hashCode() : 0);
        result = 31 * result + (nFe != null ? nFe.hashCode() : 0);
        result = 31 * result + (nZn != null ? nZn.hashCode() : 0);
        result = 31 * result + (nSe != null ? nSe.hashCode() : 0);
        result = 31 * result + (nCu != null ? nCu.hashCode() : 0);
        result = 31 * result + (nMn != null ? nMn.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "C_LATEST_MRK")
    public String getcLatestMrk() {
        return cLatestMrk;
    }

    public void setcLatestMrk(String cLatestMrk) {
        this.cLatestMrk = cLatestMrk;
    }

    @Basic
    @Column(name = "C_ORG_ID")
    public String getcOrgId() {
        return cOrgId;
    }

    public void setcOrgId(String cOrgId) {
        this.cOrgId = cOrgId;
    }

    @Basic
    @Column(name = "N_WEIGHT")
    public Integer getnWeight() {
        return nWeight;
    }

    public void setnWeight(Integer nWeight) {
        this.nWeight = nWeight;
    }

    @Basic
    @Column(name = "C_FOOD_DESC")
    public String getcFoodDesc() {
        return cFoodDesc;
    }

    public void setcFoodDesc(String cFoodDesc) {
        this.cFoodDesc = cFoodDesc;
    }
}
