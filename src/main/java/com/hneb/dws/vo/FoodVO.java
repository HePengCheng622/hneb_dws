package com.hneb.dws.vo;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
@Entity
@Table(name = "t_food")
public class FoodVO {
    private String cPkId;
    private String cName;
    private String cSecKindCde;
    private String cSecKindNme;
    private Double nEdible;
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
    private Double nGi;
    private Double nSalt;
    private Double nSugar;
    private Double nTotal;
    private String cIsDtl;
    private String cIsShow;
    private String cCde1;
    private String cCde2;

    @Id
    @Column(name = "c_pk_id")
    public String getcPkId() {
        return cPkId;
    }

    public void setcPkId(String cPkId) {
        this.cPkId = cPkId;
    }

    @Basic
    @Column(name = "c_name")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_sec_kind_cde")
    public String getcSecKindCde() {
        return cSecKindCde;
    }

    public void setcSecKindCde(String cSecKindCde) {
        this.cSecKindCde = cSecKindCde;
    }

    @Basic
    @Column(name = "c_sec_kind_nme")
    public String getcSecKindNme() {
        return cSecKindNme;
    }

    public void setcSecKindNme(String cSecKindNme) {
        this.cSecKindNme = cSecKindNme;
    }

    @Basic
    @Column(name = "n_edible")
    public Double getnEdible() {
        return nEdible;
    }

    public void setnEdible(Double nEdible) {
        this.nEdible = nEdible;
    }

    @Basic
    @Column(name = "n_water")
    public Double getnWater() {
        return nWater;
    }

    public void setnWater(Double nWater) {
        this.nWater = nWater;
    }

    @Basic
    @Column(name = "n_energy_1")
    public Double getnEnergy1() {
        return nEnergy1;
    }

    public void setnEnergy1(Double nEnergy1) {
        this.nEnergy1 = nEnergy1;
    }

    @Basic
    @Column(name = "n_energy_2")
    public Double getnEnergy2() {
        return nEnergy2;
    }

    public void setnEnergy2(Double nEnergy2) {
        this.nEnergy2 = nEnergy2;
    }

    @Basic
    @Column(name = "n_protein")
    public Double getnProtein() {
        return nProtein;
    }

    public void setnProtein(Double nProtein) {
        this.nProtein = nProtein;
    }

    @Basic
    @Column(name = "n_fat")
    public Double getnFat() {
        return nFat;
    }

    public void setnFat(Double nFat) {
        this.nFat = nFat;
    }

    @Basic
    @Column(name = "n_cho")
    public Double getnCho() {
        return nCho;
    }

    public void setnCho(Double nCho) {
        this.nCho = nCho;
    }

    @Basic
    @Column(name = "n_dietary_fiber")
    public Double getnDietaryFiber() {
        return nDietaryFiber;
    }

    public void setnDietaryFiber(Double nDietaryFiber) {
        this.nDietaryFiber = nDietaryFiber;
    }

    @Basic
    @Column(name = "n_cholesterol")
    public Double getnCholesterol() {
        return nCholesterol;
    }

    public void setnCholesterol(Double nCholesterol) {
        this.nCholesterol = nCholesterol;
    }

    @Basic
    @Column(name = "n_ash")
    public Double getnAsh() {
        return nAsh;
    }

    public void setnAsh(Double nAsh) {
        this.nAsh = nAsh;
    }

    @Basic
    @Column(name = "n_va")
    public Double getnVa() {
        return nVa;
    }

    public void setnVa(Double nVa) {
        this.nVa = nVa;
    }

    @Basic
    @Column(name = "n_carotene")
    public Double getnCarotene() {
        return nCarotene;
    }

    public void setnCarotene(Double nCarotene) {
        this.nCarotene = nCarotene;
    }

    @Basic
    @Column(name = "n_retinol")
    public Double getnRetinol() {
        return nRetinol;
    }

    public void setnRetinol(Double nRetinol) {
        this.nRetinol = nRetinol;
    }

    @Basic
    @Column(name = "n_thiamin")
    public Double getnThiamin() {
        return nThiamin;
    }

    public void setnThiamin(Double nThiamin) {
        this.nThiamin = nThiamin;
    }

    @Basic
    @Column(name = "n_riboflavin")
    public Double getnRiboflavin() {
        return nRiboflavin;
    }

    public void setnRiboflavin(Double nRiboflavin) {
        this.nRiboflavin = nRiboflavin;
    }

    @Basic
    @Column(name = "n_niacin")
    public Double getnNiacin() {
        return nNiacin;
    }

    public void setnNiacin(Double nNiacin) {
        this.nNiacin = nNiacin;
    }

    @Basic
    @Column(name = "n_vc")
    public Double getnVc() {
        return nVc;
    }

    public void setnVc(Double nVc) {
        this.nVc = nVc;
    }

    @Basic
    @Column(name = "n_ve_total")
    public Double getnVeTotal() {
        return nVeTotal;
    }

    public void setnVeTotal(Double nVeTotal) {
        this.nVeTotal = nVeTotal;
    }

    @Basic
    @Column(name = "n_ca")
    public Double getnCa() {
        return nCa;
    }

    public void setnCa(Double nCa) {
        this.nCa = nCa;
    }

    @Basic
    @Column(name = "n_p")
    public Double getnP() {
        return nP;
    }

    public void setnP(Double nP) {
        this.nP = nP;
    }

    @Basic
    @Column(name = "n_k")
    public Double getnK() {
        return nK;
    }

    public void setnK(Double nK) {
        this.nK = nK;
    }

    @Basic
    @Column(name = "n_na")
    public Double getnNa() {
        return nNa;
    }

    public void setnNa(Double nNa) {
        this.nNa = nNa;
    }

    @Basic
    @Column(name = "n_mg")
    public Double getnMg() {
        return nMg;
    }

    public void setnMg(Double nMg) {
        this.nMg = nMg;
    }

    @Basic
    @Column(name = "n_fe")
    public Double getnFe() {
        return nFe;
    }

    public void setnFe(Double nFe) {
        this.nFe = nFe;
    }

    @Basic
    @Column(name = "n_zn")
    public Double getnZn() {
        return nZn;
    }

    public void setnZn(Double nZn) {
        this.nZn = nZn;
    }

    @Basic
    @Column(name = "n_se")
    public Double getnSe() {
        return nSe;
    }

    public void setnSe(Double nSe) {
        this.nSe = nSe;
    }

    @Basic
    @Column(name = "n_cu")
    public Double getnCu() {
        return nCu;
    }

    public void setnCu(Double nCu) {
        this.nCu = nCu;
    }

    @Basic
    @Column(name = "n_mn")
    public Double getnMn() {
        return nMn;
    }

    public void setnMn(Double nMn) {
        this.nMn = nMn;
    }

    @Basic
    @Column(name = "n_gi")
    public Double getnGi() {
        return nGi;
    }

    public void setnGi(Double nGi) {
        this.nGi = nGi;
    }

    @Basic
    @Column(name = "n_salt")
    public Double getnSalt() {
        return nSalt;
    }

    public void setnSalt(Double nSalt) {
        this.nSalt = nSalt;
    }

    @Basic
    @Column(name = "n_sugar")
    public Double getnSugar() {
        return nSugar;
    }

    public void setnSugar(Double nSugar) {
        this.nSugar = nSugar;
    }

    @Basic
    @Column(name = "n_total")
    public Double getnTotal() {
        return nTotal;
    }

    public void setnTotal(Double nTotal) {
        this.nTotal = nTotal;
    }

    @Basic
    @Column(name = "c_is_dtl")
    public String getcIsDtl() {
        return cIsDtl;
    }

    public void setcIsDtl(String cIsDtl) {
        this.cIsDtl = cIsDtl;
    }

    @Basic
    @Column(name = "c_is_show")
    public String getcIsShow() {
        return cIsShow;
    }

    public void setcIsShow(String cIsShow) {
        this.cIsShow = cIsShow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodVO foodVO = (FoodVO) o;

        if (cPkId != null ? !cPkId.equals(foodVO.cPkId) : foodVO.cPkId != null) return false;
        if (cName != null ? !cName.equals(foodVO.cName) : foodVO.cName != null) return false;
        if (cSecKindCde != null ? !cSecKindCde.equals(foodVO.cSecKindCde) : foodVO.cSecKindCde != null) return false;
        if (cSecKindNme != null ? !cSecKindNme.equals(foodVO.cSecKindNme) : foodVO.cSecKindNme != null) return false;
        if (nEdible != null ? !nEdible.equals(foodVO.nEdible) : foodVO.nEdible != null) return false;
        if (nWater != null ? !nWater.equals(foodVO.nWater) : foodVO.nWater != null) return false;
        if (nEnergy1 != null ? !nEnergy1.equals(foodVO.nEnergy1) : foodVO.nEnergy1 != null) return false;
        if (nEnergy2 != null ? !nEnergy2.equals(foodVO.nEnergy2) : foodVO.nEnergy2 != null) return false;
        if (nProtein != null ? !nProtein.equals(foodVO.nProtein) : foodVO.nProtein != null) return false;
        if (nFat != null ? !nFat.equals(foodVO.nFat) : foodVO.nFat != null) return false;
        if (nCho != null ? !nCho.equals(foodVO.nCho) : foodVO.nCho != null) return false;
        if (nDietaryFiber != null ? !nDietaryFiber.equals(foodVO.nDietaryFiber) : foodVO.nDietaryFiber != null)
            return false;
        if (nCholesterol != null ? !nCholesterol.equals(foodVO.nCholesterol) : foodVO.nCholesterol != null)
            return false;
        if (nAsh != null ? !nAsh.equals(foodVO.nAsh) : foodVO.nAsh != null) return false;
        if (nVa != null ? !nVa.equals(foodVO.nVa) : foodVO.nVa != null) return false;
        if (nCarotene != null ? !nCarotene.equals(foodVO.nCarotene) : foodVO.nCarotene != null) return false;
        if (nRetinol != null ? !nRetinol.equals(foodVO.nRetinol) : foodVO.nRetinol != null) return false;
        if (nThiamin != null ? !nThiamin.equals(foodVO.nThiamin) : foodVO.nThiamin != null) return false;
        if (nRiboflavin != null ? !nRiboflavin.equals(foodVO.nRiboflavin) : foodVO.nRiboflavin != null) return false;
        if (nNiacin != null ? !nNiacin.equals(foodVO.nNiacin) : foodVO.nNiacin != null) return false;
        if (nVc != null ? !nVc.equals(foodVO.nVc) : foodVO.nVc != null) return false;
        if (nVeTotal != null ? !nVeTotal.equals(foodVO.nVeTotal) : foodVO.nVeTotal != null) return false;
        if (nCa != null ? !nCa.equals(foodVO.nCa) : foodVO.nCa != null) return false;
        if (nP != null ? !nP.equals(foodVO.nP) : foodVO.nP != null) return false;
        if (nK != null ? !nK.equals(foodVO.nK) : foodVO.nK != null) return false;
        if (nNa != null ? !nNa.equals(foodVO.nNa) : foodVO.nNa != null) return false;
        if (nMg != null ? !nMg.equals(foodVO.nMg) : foodVO.nMg != null) return false;
        if (nFe != null ? !nFe.equals(foodVO.nFe) : foodVO.nFe != null) return false;
        if (nZn != null ? !nZn.equals(foodVO.nZn) : foodVO.nZn != null) return false;
        if (nSe != null ? !nSe.equals(foodVO.nSe) : foodVO.nSe != null) return false;
        if (nCu != null ? !nCu.equals(foodVO.nCu) : foodVO.nCu != null) return false;
        if (nMn != null ? !nMn.equals(foodVO.nMn) : foodVO.nMn != null) return false;
        if (nGi != null ? !nGi.equals(foodVO.nGi) : foodVO.nGi != null) return false;
        if (nSalt != null ? !nSalt.equals(foodVO.nSalt) : foodVO.nSalt != null) return false;
        if (nSugar != null ? !nSugar.equals(foodVO.nSugar) : foodVO.nSugar != null) return false;
        if (nTotal != null ? !nTotal.equals(foodVO.nTotal) : foodVO.nTotal != null) return false;
        if (cIsDtl != null ? !cIsDtl.equals(foodVO.cIsDtl) : foodVO.cIsDtl != null) return false;
        if (cIsShow != null ? !cIsShow.equals(foodVO.cIsShow) : foodVO.cIsShow != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cPkId != null ? cPkId.hashCode() : 0;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cSecKindCde != null ? cSecKindCde.hashCode() : 0);
        result = 31 * result + (cSecKindNme != null ? cSecKindNme.hashCode() : 0);
        result = 31 * result + (nEdible != null ? nEdible.hashCode() : 0);
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
        result = 31 * result + (nGi != null ? nGi.hashCode() : 0);
        result = 31 * result + (nSalt != null ? nSalt.hashCode() : 0);
        result = 31 * result + (nSugar != null ? nSugar.hashCode() : 0);
        result = 31 * result + (nTotal != null ? nTotal.hashCode() : 0);
        result = 31 * result + (cIsDtl != null ? cIsDtl.hashCode() : 0);
        result = 31 * result + (cIsShow != null ? cIsShow.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "C_CDE1")
    public String getcCde1() {
        return cCde1;
    }

    public void setcCde1(String cCde1) {
        this.cCde1 = cCde1;
    }

    @Basic
    @Column(name = "C_CDE2")
    public String getcCde2() {
        return cCde2;
    }

    public void setcCde2(String cCde2) {
        this.cCde2 = cCde2;
    }
}
