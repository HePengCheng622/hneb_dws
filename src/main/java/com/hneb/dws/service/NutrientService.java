package com.hneb.dws.service;

import com.hneb.dws.dao.NutrientDao;
import com.hneb.dws.dao.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 */
@Service
public class NutrientService {


    @Autowired
    private RecipeService recipeService;
    @Autowired
    private NutrientDao nutrientDao;


//            年龄（岁）	总碳水化合物	总脂肪
//            0-	        -	            48(AI)
//            0.5-	        -  	            40(AI)
//            1-	        50-65	        35(AI)
//            4-	        50-65	        20-30
//            7-	        50-65	        20-30
//            11-	        50-65	        20-30
//            14-	        50-65	        20-30
//            18-	        50-65	        20-30

    /**
     * 计算各年龄需要多少碳水化合物、脂肪和蛋白质（单位克）
     * 分别返回 最小-中值-最大
     * dbz      蛋白质
     * zf       脂肪
     * tshhw    碳水化合物
     * @param age 年龄
     * @param protein 蛋白质
     * @param energy 能力（千卡）
     * @return
     */
    public Map<String ,String> getEnergy(float age,float protein,float energy){
        String tshhw;//碳水化合物
        String zf;//脂肪
        Map<String,String> map = new HashMap<String,String>();
        if(age<1){
            double f1 = protein*4/energy;//蛋白质供能比
            double f2 = 48/100.0;//脂肪供能比
            double f3 = 1-f1-f2;//碳水化合物供能比

            double f2_ = f2*energy;
            double f3_ = f3*energy;

            map.put("dbz",protein+","+protein+","+protein);//蛋白质 摄入量，单位g，最小,建议,最大
            map.put("zf",f2_/9.0+","+f2_/9.0+","+f2_/9.0);//脂肪
            map.put("tshhw",f3_/4.0+","+f3_/4.0+","+f3_/4.0);//碳水化合物

            return map;
        }else if(age==1){
            double f1 = protein*4/energy;//蛋白质供能比
            double f2 = 35/100.0;//脂肪供能比
            double f3 = 1-f1-f2;//碳水化合物供能比

            double f2_ = f2*energy;
            double f3_ = f3*energy;

            map.put("dbz",protein+","+protein+","+protein);//蛋白质 摄入量，单位g，最小,建议,最大
            map.put("zf",f2_/9.0+","+f2_/9.0+","+f2_/9.0);//脂肪
            map.put("tshhw",50*energy/100.0/4.0+","+f3_/4.0+","+65*energy/100.0/4.0);//碳水化合物

            return map;
        }else{
            double f1 = protein*4/energy;//蛋白质供能比
            double f2 = 25/100.0;//脂肪供能比（脂肪是20-30，取中数25）
            double f3 = 1-f1-f2;//碳水化合物供能比

            double f2_ = f2*energy;
            double f3_ = f3*energy;

            map.put("dbz",protein+","+protein+","+protein);//蛋白质 摄入量，单位g，最小-建议-最大
            map.put("zf",20*energy/100.0/9.0+","+f2_/9.0+","+30*energy/100.0/9.0);//脂肪
            map.put("tshhw",50*energy/100.0/4.0+","+f3_/4.0+","+65*energy/100.0/4.0);//碳水化合物

            return map;
        }
    }


    public Map<String,Double> calcResult(NutrientService.NutrientBo nutrientBo,Map<String,String> energy,Map<String,Double> sum){
        Map result = new HashMap<String,Double>();

        Double dbz_min = Double.parseDouble(energy.get("dbz").split(",")[0]);
        Double dbz = Double.parseDouble(energy.get("dbz").split(",")[1]);
        Double dbz_max = Double.parseDouble(energy.get("dbz").split(",")[2]);

        Double tshhw_min =Double.parseDouble(energy.get("tshhw").split(",")[0]);
        Double tshhw =Double.parseDouble(energy.get("tshhw").split(",")[1]);
        Double tshhw_max =Double.parseDouble(energy.get("tshhw").split(",")[2]);

        Double zf_min = Double.parseDouble(energy.get("zf").split(",")[0]);
        Double zf = Double.parseDouble(energy.get("zf").split(",")[1]);
        Double zf_max = Double.parseDouble(energy.get("zf").split(",")[2]);


        result.put("dbz",calc_1(dbz_min,dbz_max,Double.parseDouble(sum.get("protein").toString())));
        result.put("zf",calc_1(zf_min,zf_max,Double.parseDouble(sum.get("fat").toString())));
        result.put("tshhw",calc_1(tshhw_min,tshhw_max,Double.parseDouble(sum.get("cho").toString())));

        result.put("ca",calc_2(nutrientBo.getCa_suggest(),nutrientBo.getCa_ul(),Float.parseFloat(sum.get("ca").toString())));
        result.put("p",calc_2(nutrientBo.getP_suggest(),nutrientBo.getP_ul(),Float.parseFloat(sum.get("p").toString())));
        result.put("k",calc_2(nutrientBo.getK_suggest(),nutrientBo.getK_ul(),Float.parseFloat(sum.get("k").toString())));
        result.put("mg",calc_2(nutrientBo.getMg_suggest(),nutrientBo.getMg_ul(),Float.parseFloat(sum.get("mg").toString())));
        result.put("na",calc_2(nutrientBo.getNa_suggest(),nutrientBo.getNa_ul(),Float.parseFloat(sum.get("na").toString())));
        result.put("fe",calc_2(nutrientBo.getFe_suggest(),nutrientBo.getFe_ul(),Float.parseFloat(sum.get("fe").toString())));
        result.put("zn",calc_2(nutrientBo.getZn_suggest(),nutrientBo.getZn_ul(),Float.parseFloat(sum.get("zn").toString())));
        result.put("se",calc_2(nutrientBo.getSe_suggest(),nutrientBo.getSe_ul(),Float.parseFloat(sum.get("se").toString())));
        result.put("cu",calc_2(nutrientBo.getCu_suggest(),nutrientBo.getCu_ul(),Float.parseFloat(sum.get("cu").toString())));
        result.put("mn",calc_2(nutrientBo.getMn_suggest(),nutrientBo.getMn_ul(),Float.parseFloat(sum.get("mn").toString())));
        result.put("va",calc_2(nutrientBo.getVa_suggest(),nutrientBo.getVa_ul(),Float.parseFloat(sum.get("va").toString())));
//        result.put("ve",calc_2(nutrientBo.getVe_suggest(),nutrientBo.getVe_ul(),Float.parseFloat(sum.get("ve").toString())));
        result.put("vc",calc_2(nutrientBo.getVc_suggest(),nutrientBo.getVc_ul(),Float.parseFloat(sum.get("vc").toString())));

        return result;
    }


    private Double calc_1(Double min,Double max,Double actural){
        if(actural<min){
            return actural-min;
        }
        else if(actural>=min && actural<=max){
            return 0.0d;
        }
        else{
            return actural-max;
        }
    }
    private Float calc_2(Float min,Float max,Float actural){
        if(max==-1){
            if(actural<min){
                return actural-min;
            }
            if(actural==min){
                return 0.0f;
            }
            else{
                return actural-min;
            }
        }
        else{
            if(actural<min){
                return actural-min;
            }
            if(actural>=min && actural<=max){
                return 0.0f;
            }
            else{
                return actural-max;
            }
        }
    }

//    public void calc(String sex, String type,Float age,String carteId,String dateId) throws Exception {
//        NutrientBo nutrientBo = this.get(sex,type,age);
//        Map<String,String> tmp = this.getEnergy(age,nutrientBo.getProtein(),nutrientBo.getEnergy());
//        Map<String,Double> map = this.recipeService.sum(carteId,dateId);
//
//    }


    /**
     *
     * @param sex 性别M男，F女
     * @param type 类型，1轻体力，2中体力，3重体力
     * @param age 年龄
     * @return
     */
    public NutrientBo get(String sex, String type,Float age) throws Exception {
        NutrientBo bo = new NutrientBo();
        bo.setEnergy(this.nutrientDao.get("kcal/d",sex,type,age));
        bo.setProtein(this.nutrientDao.get("Protein",sex,"RNI",age));
        bo.setCarbohydrate(this.nutrientDao.get("carbohydrate","*",null,age));

        bo.setCa_suggest(this.nutrientDao.get("Ca","*","RNI",age));
        bo.setCa_ul(this.nutrientDao.get("Ca","*","UL",age));

        bo.setP_suggest(this.nutrientDao.get("P","*","RNI",age));
        bo.setP_ul(this.nutrientDao.get("P","*","UL",age));

        bo.setK_suggest(this.nutrientDao.get("K","*","PI",age));
        bo.setK_ul(-1);

        bo.setMg_suggest(this.nutrientDao.get("Mg","*","RNI",age));
        bo.setMg_ul(-1);

        bo.setNa_suggest(this.nutrientDao.get("Na","*","PI",age));
        bo.setNa_ul(-1);

        bo.setFe_suggest(this.nutrientDao.get("Fe",sex,"RNI",age));
        bo.setFe_ul(this.nutrientDao.get("Fe","*","UL",age));

        bo.setZn_suggest(this.nutrientDao.get("Zn",sex,"RNI",age));
        bo.setZn_ul(this.nutrientDao.get("Zn","*","UL",age));

        bo.setSe_suggest(this.nutrientDao.get("Se","*","RNI",age));
        bo.setSe_ul(this.nutrientDao.get("Se","*","UL",age));

        bo.setCu_suggest(this.nutrientDao.get("Cu","*","RNI",age));
        bo.setCu_ul(this.nutrientDao.get("Cu","*","UL",age));

        bo.setMn_suggest(this.nutrientDao.get("Mn","*","AI",age));
        bo.setMn_ul(this.nutrientDao.get("Mn","*","UL",age));

        bo.setVa_suggest(this.nutrientDao.get("Va",sex,"RNI",age));
        bo.setVa_ul(this.nutrientDao.get("Va","*","UL",age));

        bo.setVe_suggest(this.nutrientDao.get("Ve","*","AI",age));
        bo.setVe_ul(this.nutrientDao.get("Ve","*","UL",age));

        bo.setVc_suggest(this.nutrientDao.get("Vc","*","RNI",age));
        bo.setVc_ul(this.nutrientDao.get("Vc","*","UL",age));

        System.out.println(bo.toString());
        return bo;
    }


    public class NutrientBo{
        float energy;
        float protein;
        float carbohydrate;

        float ca_suggest;
        float p_suggest;
        float k_suggest;
        float mg_suggest;
        float na_suggest;
        float fe_suggest;
        float zn_suggest;
        float se_suggest;
        float cu_suggest;
        float mn_suggest;
        float va_suggest;
        float ve_suggest;
        float vc_suggest;

        float ca_ul=-1;//默认为-1，如果数据库中没有则为-1
        float p_ul=-1;
        float k_ul=-1;
        float mg_ul=-1;
        float na_ul=-1;
        float fe_ul=-1;
        float zn_ul=-1;
        float se_ul=-1;
        float cu_ul=-1;
        float mn_ul=-1;
        float va_ul=-1;
        float ve_ul=-1;
        float vc_ul=-1;

        public float getEnergy() {
            return energy;
        }

        public void setEnergy(float energy) {
            this.energy = energy;
        }

        public float getProtein() {
            return protein;
        }

        public void setProtein(float protein) {
            this.protein = protein;
        }

        public float getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(float carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public float getCa_suggest() {
            return ca_suggest;
        }

        public void setCa_suggest(float ca_suggest) {
            this.ca_suggest = ca_suggest;
        }

        public float getP_suggest() {
            return p_suggest;
        }

        public void setP_suggest(float p_suggest) {
            this.p_suggest = p_suggest;
        }

        public float getK_suggest() {
            return k_suggest;
        }

        public void setK_suggest(float k_suggest) {
            this.k_suggest = k_suggest;
        }

        public float getMg_suggest() {
            return mg_suggest;
        }

        public void setMg_suggest(float mg_suggest) {
            this.mg_suggest = mg_suggest;
        }

        public float getNa_suggest() {
            return na_suggest;
        }

        public void setNa_suggest(float na_suggest) {
            this.na_suggest = na_suggest;
        }

        public float getFe_suggest() {
            return fe_suggest;
        }

        public void setFe_suggest(float fe_suggest) {
            this.fe_suggest = fe_suggest;
        }

        public float getZn_suggest() {
            return zn_suggest;
        }

        public void setZn_suggest(float zn_suggest) {
            this.zn_suggest = zn_suggest;
        }

        public float getSe_suggest() {
            return se_suggest;
        }

        public void setSe_suggest(float se_suggest) {
            this.se_suggest = se_suggest;
        }

        public float getCu_suggest() {
            return cu_suggest;
        }

        public void setCu_suggest(float cu_suggest) {
            this.cu_suggest = cu_suggest;
        }

        public float getMn_suggest() {
            return mn_suggest;
        }

        public void setMn_suggest(float mn_suggest) {
            this.mn_suggest = mn_suggest;
        }

        public float getCa_ul() {
            return ca_ul;
        }

        public void setCa_ul(float ca_ul) {
            this.ca_ul = ca_ul;
        }

        public float getP_ul() {
            return p_ul;
        }

        public void setP_ul(float p_ul) {
            this.p_ul = p_ul;
        }

        public float getK_ul() {
            return k_ul;
        }

        public void setK_ul(float k_ul) {
            this.k_ul = k_ul;
        }

        public float getMg_ul() {
            return mg_ul;
        }

        public void setMg_ul(float mg_ul) {
            this.mg_ul = mg_ul;
        }

        public float getNa_ul() {
            return na_ul;
        }

        public void setNa_ul(float na_ul) {
            this.na_ul = na_ul;
        }

        public float getFe_ul() {
            return fe_ul;
        }

        public void setFe_ul(float fe_ul) {
            this.fe_ul = fe_ul;
        }

        public float getZn_ul() {
            return zn_ul;
        }

        public void setZn_ul(float zn_ul) {
            this.zn_ul = zn_ul;
        }

        public float getSe_ul() {
            return se_ul;
        }

        public void setSe_ul(float se_ul) {
            this.se_ul = se_ul;
        }

        public float getCu_ul() {
            return cu_ul;
        }

        public void setCu_ul(float cu_ul) {
            this.cu_ul = cu_ul;
        }

        public float getMn_ul() {
            return mn_ul;
        }

        public void setMn_ul(float mn_ul) {
            this.mn_ul = mn_ul;
        }

        public float getVa_suggest() {
            return va_suggest;
        }

        public void setVa_suggest(float va_suggest) {
            this.va_suggest = va_suggest;
        }

        public float getVe_suggest() {
            return ve_suggest;
        }

        public void setVe_suggest(float ve_suggest) {
            this.ve_suggest = ve_suggest;
        }

        public float getVc_suggest() {
            return vc_suggest;
        }

        public void setVc_suggest(float vc_suggest) {
            this.vc_suggest = vc_suggest;
        }

        public float getVa_ul() {
            return va_ul;
        }

        public void setVa_ul(float va_ul) {
            this.va_ul = va_ul;
        }

        public float getVe_ul() {
            return ve_ul;
        }

        public void setVe_ul(float ve_ul) {
            this.ve_ul = ve_ul;
        }

        public float getVc_ul() {
            return vc_ul;
        }

        public void setVc_ul(float vc_ul) {
            this.vc_ul = vc_ul;
        }

        @Override
        public String toString() {
            return "NutrientBo{" +
                    "energy=" + energy +
                    ", protein=" + protein +
                    ", carbohydrate=" + carbohydrate +
                    ", ca_suggest=" + ca_suggest +
                    ", p_suggest=" + p_suggest +
                    ", k_suggest=" + k_suggest +
                    ", mg_suggest=" + mg_suggest +
                    ", na_suggest=" + na_suggest +
                    ", fe_suggest=" + fe_suggest +
                    ", zn_suggest=" + zn_suggest +
                    ", se_suggest=" + se_suggest +
                    ", cu_suggest=" + cu_suggest +
                    ", mn_suggest=" + mn_suggest +
                    ", va_suggest=" + va_suggest +
                    ", ve_suggest=" + ve_suggest +
                    ", vc_suggest=" + vc_suggest +
                    ", ca_ul=" + ca_ul +
                    ", p_ul=" + p_ul +
                    ", k_ul=" + k_ul +
                    ", mg_ul=" + mg_ul +
                    ", na_ul=" + na_ul +
                    ", fe_ul=" + fe_ul +
                    ", zn_ul=" + zn_ul +
                    ", se_ul=" + se_ul +
                    ", cu_ul=" + cu_ul +
                    ", mn_ul=" + mn_ul +
                    ", va_ul=" + va_ul +
                    ", ve_ul=" + ve_ul +
                    ", vc_ul=" + vc_ul +
                    '}';
        }
    };
}
