package com.hneb.dws.service;

import com.hneb.dws.dao.FoodDao;
import com.hneb.dws.dao.RecipeDao;
import com.hneb.dws.dao.RecipeFoodDao;
import com.hneb.dws.vo.FoodVO;
import com.hneb.dws.vo.RecipeFoodVO;
import com.hneb.dws.vo.RecipeVO;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.DateUtils;
import com.hneb.fwk.utils.VoUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/12/14.
 */
@Service
public class RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private RecipeFoodDao recipeFoodDao;

    @Autowired
    private FoodDao foodDao;

    /**
     * 根据餐单id和食用日期计算当天所有营养素的总和
     * @param carteId
     * @param dateId
     * @return
     * @throws Exception
     */
    public Map<String,Double> sum(String carteId, String dateId) throws Exception {
        List<Map> list = this.recipeDao.getRecipe(carteId,dateId);

        Map<String,Double> map = new HashMap<String,Double>();
        map.put("water",0.0);
        map.put("energy1",0.0);
        map.put("energy2",0.0);
        map.put("protein",0.0);
        map.put("fat",0.0);
        map.put("cho",0.0);
        map.put("dietaryFiber",0.0);
        map.put("cholesterol",0.0);
        map.put("ash",0.0);
        map.put("va",0.0);
        map.put("carotene",0.0);
        map.put("retinol",0.0);
        map.put("thiamin",0.0);
        map.put("riboflavin",0.0);
        map.put("niacin",0.0);
        map.put("vc",0.0);
        map.put("veTotal",0.0);
        map.put("ca",0.0);
        map.put("p",0.0);
        map.put("k",0.0);
        map.put("na",0.0);
        map.put("mg",0.0);
        map.put("fe",0.0);
        map.put("zn",0.0);
        map.put("se",0.0);
        map.put("cu",0.0);
        map.put("mn",0.0);

        for(Map m:list){
            map.put("water",map.get("water")+Double.parseDouble(m.get("water").toString()));
            map.put("energy1",map.get("energy1")+Double.parseDouble(m.get("energy1").toString()));
            map.put("energy2",map.get("energy2")+Double.parseDouble(m.get("energy2").toString()));
            map.put("protein",map.get("protein")+Double.parseDouble(m.get("protein").toString()));
            map.put("fat",map.get("fat")+Double.parseDouble(m.get("fat").toString()));
            map.put("cho",map.get("cho")+Double.parseDouble(m.get("cho").toString()));
            map.put("dietaryFiber",map.get("dietaryFiber")+Double.parseDouble(m.get("dietaryFiber").toString()));
            map.put("cholesterol",map.get("cholesterol")+Double.parseDouble(m.get("cholesterol").toString()));
            map.put("ash",map.get("ash")+Double.parseDouble(m.get("ash").toString()));
            map.put("va",map.get("va")+Double.parseDouble(m.get("va").toString()));
            map.put("carotene",map.get("carotene")+Double.parseDouble(m.get("carotene").toString()));
            map.put("retinol",map.get("retinol")+Double.parseDouble(m.get("retinol").toString()));
            map.put("thiamin",map.get("thiamin")+Double.parseDouble(m.get("thiamin").toString()));
            map.put("riboflavin",map.get("riboflavin")+Double.parseDouble(m.get("riboflavin").toString()));
            map.put("niacin",map.get("niacin")+Double.parseDouble(m.get("niacin").toString()));
            map.put("vc",map.get("vc")+Double.parseDouble(m.get("vc").toString()));
            map.put("veTotal",map.get("veTotal")+Double.parseDouble(m.get("veTotal").toString()));
            map.put("ca",map.get("ca")+Double.parseDouble(m.get("ca").toString()));
            map.put("p",map.get("p")+Double.parseDouble(m.get("p").toString()));
            map.put("k",map.get("k")+Double.parseDouble(m.get("k").toString()));
            map.put("na",map.get("na")+Double.parseDouble(m.get("na").toString()));
            map.put("mg",map.get("mg")+Double.parseDouble(m.get("mg").toString()));
            map.put("fe",map.get("fe")+Double.parseDouble(m.get("fe").toString()));
            map.put("zn",map.get("zn")+Double.parseDouble(m.get("zn").toString()));
            map.put("se",map.get("se")+Double.parseDouble(m.get("se").toString()));
            map.put("cu",map.get("cu")+Double.parseDouble(m.get("cu").toString()));
            map.put("mn",map.get("mn")+Double.parseDouble(m.get("mn").toString()));
        }
        return map;
    }

    /**
     * 生成食材描述
     * @param list
     * @return
     */
    public String generateDesc(List<RecipeFoodVO> list){
        StringBuffer sb = new StringBuffer();
        for(RecipeFoodVO vo : list){
            sb.append(vo.getcFoodNme()+vo.getnWeight()+"克 , ");
        }
        String tmp = sb.toString();
        return "".equals(tmp)?"":tmp.substring(0,tmp.length()-1);//去掉最后的逗号
    }
    /**
     * 副本，供菜品前台列表中的按钮【副本】调用
     * @param pkId
     * @return
     */
    public String copy(String pkId){
        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();

        RecipeVO vo = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(pkId);
        RecipeVO copy = new RecipeVO();
        BeanUtils.copyProperties(vo,copy);

        String recipeId = UUID.randomUUID().toString();
        copy.setcPkId(recipeId);
        copy.setcOrgId(recipeId);//copy出来的副本这道菜可以随意更改，跟原来的没关系了
        copy.setcUserId(userId);
        copy.setcLatestMrk("1");
        copy.setcName(copy.getcName()+"_副本"/*+ DateUtils.dateToStr(now,"yyyyMMddHHmmss")*/);

        VoUtils.touchOnCreate(copy,now,userId);
        copy = this.recipeDao.getRecipeRep().saveAndFlush(copy);


        List<RecipeFoodVO> list = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(vo.getcPkId());

        for(RecipeFoodVO food : list){
            RecipeFoodVO tmp = new RecipeFoodVO();
            BeanUtils.copyProperties(food,tmp);
            VoUtils.touchOnCreate(tmp,now,userId);
            tmp.setcPkId(null);
            tmp.setcRecipeId(recipeId);
            this.recipeFoodDao.getRecipeFoodRep().save(tmp);
        }

        return copy.getcPkId();
    }


    /**
     * 拷贝RecipeVO对象，并设置相关的值，供餐单中修改重量时 需要新增菜品时使用
     * 注意：该方法并没有调用保存
     * @param vo
     * @return
     */
    public RecipeVO copyRecipe(RecipeVO vo){
        RecipeVO copy = new RecipeVO();
        BeanUtils.copyProperties(vo,copy);

        String recipeId = UUID.randomUUID().toString();
        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();
        copy.setcPkId(recipeId);
        copy.setcOrgId(vo.getcPkId());//还是用原来的orgId，因为这个方法上层调用只该重量，其他的不改。因此copy出来的这道菜还是那道菜
        copy.setcUserId(userId);
        copy.setcLatestMrk("0");
        copy.setcName(copy.getcName());

        VoUtils.touchOnCreate(copy,now,userId);

        return copy;
    }

    /**
     * 拷贝菜谱中的食材，并设置相关字段
     * 注意：该方法并没有调用保存
     * @param list RecipeFoodListd对象
     * @param recipeId 拷贝后的食材关联到哪个菜品id中去
     * @return
     */
    public List<RecipeFoodVO> copyRecipeFood(List<RecipeFoodVO> list,String recipeId){
        List<RecipeFoodVO> copy = new ArrayList<RecipeFoodVO>();

        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();
        for(RecipeFoodVO food : list){
            RecipeFoodVO tmp = new RecipeFoodVO();
            BeanUtils.copyProperties(food,tmp);
            tmp.setcPkId(null);
            VoUtils.touchOnCreate(tmp,now,userId);
            tmp.setcRecipeId(recipeId);
            copy.add(tmp);
        }

        return copy;
    }

    public void setDefualtValue(RecipeVO recipeVO){
        recipeVO.setnWeight(recipeVO.getnWeight()==null?0:recipeVO.getnWeight());

        recipeVO.setnWater((recipeVO.getnWater()==null?0:recipeVO.getnWater()));
        recipeVO.setnEnergy1((recipeVO.getnEnergy1()==null?0:recipeVO.getnEnergy1()));
        recipeVO.setnEnergy2((recipeVO.getnEnergy2()==null?0:recipeVO.getnEnergy2()));
        recipeVO.setnProtein((recipeVO.getnProtein()==null?0:recipeVO.getnProtein()));
        recipeVO.setnFat((recipeVO.getnFat()==null?0:recipeVO.getnFat()));
        recipeVO.setnCho((recipeVO.getnCho()==null?0:recipeVO.getnCho()));
        recipeVO.setnDietaryFiber((recipeVO.getnDietaryFiber()==null?0:recipeVO.getnDietaryFiber()));
        recipeVO.setnCholesterol((recipeVO.getnCholesterol()==null?0:recipeVO.getnCholesterol()));
        recipeVO.setnAsh((recipeVO.getnAsh()==null?0:recipeVO.getnAsh()));
        recipeVO.setnVa((recipeVO.getnVa()==null?0:recipeVO.getnVa()));
        recipeVO.setnCarotene((recipeVO.getnCarotene()==null?0:recipeVO.getnCarotene()));
        recipeVO.setnRetinol((recipeVO.getnRetinol()==null?0:recipeVO.getnRetinol()));
        recipeVO.setnThiamin((recipeVO.getnThiamin()==null?0:recipeVO.getnThiamin()));
        recipeVO.setnRiboflavin((recipeVO.getnRiboflavin()==null?0:recipeVO.getnRiboflavin()));
        recipeVO.setnNiacin((recipeVO.getnNiacin()==null?0:recipeVO.getnNiacin()));
        recipeVO.setnVc((recipeVO.getnVc()==null?0:recipeVO.getnVc()));
        recipeVO.setnVeTotal((recipeVO.getnVeTotal()==null?0:recipeVO.getnVeTotal()));
        recipeVO.setnCa((recipeVO.getnCa()==null?0:recipeVO.getnCa()));
        recipeVO.setnP((recipeVO.getnP()==null?0:recipeVO.getnP()));
        recipeVO.setnK((recipeVO.getnK()==null?0:recipeVO.getnK()));
        recipeVO.setnNa((recipeVO.getnNa()==null?0:recipeVO.getnNa()));
        recipeVO.setnMg((recipeVO.getnMg()==null?0:recipeVO.getnMg()));
        recipeVO.setnFe((recipeVO.getnFe()==null?0:recipeVO.getnFe()));
        recipeVO.setnZn((recipeVO.getnZn()==null?0:recipeVO.getnZn()));
        recipeVO.setnSe((recipeVO.getnSe()==null?0:recipeVO.getnSe()));
        recipeVO.setnCu((recipeVO.getnCu()==null?0:recipeVO.getnCu()));
        recipeVO.setnMn((recipeVO.getnMn()==null?0:recipeVO.getnMn()));
    }

    /**
     * 计算营养值
     * @param recipeVO
     * @param list
     */
    public void calcNutrient(RecipeVO recipeVO,List<RecipeFoodVO> list){
        //先置空
        recipeVO.setnWater(0d);
        recipeVO.setnEnergy1(0d);
        recipeVO.setnEnergy2(0d);
        recipeVO.setnProtein(0d);
        recipeVO.setnFat(0d);
        recipeVO.setnCho(0d);
        recipeVO.setnDietaryFiber(0d);
        recipeVO.setnCholesterol(0d);
        recipeVO.setnAsh(0d);
        recipeVO.setnVa(0d);
        recipeVO.setnCarotene(0d);
        recipeVO.setnRetinol(0d);
        recipeVO.setnThiamin(0d);
        recipeVO.setnRiboflavin(0d);
        recipeVO.setnNiacin(0d);
        recipeVO.setnVc(0d);
        recipeVO.setnCa(0d);
        recipeVO.setnP(0d);
        recipeVO.setnK(0d);
        recipeVO.setnNa(0d);
        recipeVO.setnMg(0d);
        recipeVO.setnFe(0d);
        recipeVO.setnZn(0d);
        recipeVO.setnSe(0d);
        recipeVO.setnCu(0d);
        recipeVO.setnMn(0d);
        
        for (RecipeFoodVO obj : list) {
            String foodId = obj.getcFoodId();
            double weight = obj.getnWeight();
            FoodVO vo = foodDao.getFoodRep().getFoodVOByCPkId(foodId);
            recipeVO.setnWater((weight/100)*vo.getnWater()+(recipeVO.getnWater()==null?0:recipeVO.getnWater()));
            recipeVO.setnEnergy1((weight/100)*vo.getnEnergy1()+(recipeVO.getnEnergy1()==null?0:recipeVO.getnEnergy1()));
            recipeVO.setnEnergy2((weight/100)*vo.getnEnergy2()+(recipeVO.getnEnergy2()==null?0:recipeVO.getnEnergy2()));
            recipeVO.setnProtein((weight/100)*vo.getnProtein()+(recipeVO.getnProtein()==null?0:recipeVO.getnProtein()));
            recipeVO.setnFat((weight/100)*vo.getnFat()+(recipeVO.getnFat()==null?0:recipeVO.getnFat()));
            recipeVO.setnCho((weight/100)*vo.getnCho()+(recipeVO.getnCho()==null?0:recipeVO.getnCho()));
            recipeVO.setnDietaryFiber((weight/100)*vo.getnDietaryFiber()+(recipeVO.getnDietaryFiber()==null?0:recipeVO.getnDietaryFiber()));
            recipeVO.setnCholesterol((weight/100)*vo.getnCholesterol()+(recipeVO.getnCholesterol()==null?0:recipeVO.getnCholesterol()));
            recipeVO.setnAsh((weight/100)*vo.getnAsh()+(recipeVO.getnAsh()==null?0:recipeVO.getnAsh()));
            recipeVO.setnVa((weight/100)*vo.getnVa()+(recipeVO.getnVa()==null?0:recipeVO.getnVa()));
            recipeVO.setnCarotene((weight/100)*vo.getnCarotene()+(recipeVO.getnCarotene()==null?0:recipeVO.getnCarotene()));
            recipeVO.setnRetinol((weight/100)*vo.getnRetinol()+(recipeVO.getnRetinol()==null?0:recipeVO.getnRetinol()));
            recipeVO.setnThiamin((weight/100)*vo.getnThiamin()+(recipeVO.getnThiamin()==null?0:recipeVO.getnThiamin()));
            recipeVO.setnRiboflavin((weight/100)*vo.getnRiboflavin()+(recipeVO.getnRiboflavin()==null?0:recipeVO.getnRiboflavin()));
            recipeVO.setnNiacin((weight/100)*vo.getnNiacin()+(recipeVO.getnNiacin()==null?0:recipeVO.getnNiacin()));
            recipeVO.setnVc((weight/100)*vo.getnVc()+(recipeVO.getnVc()==null?0:recipeVO.getnVc()));
            recipeVO.setnVeTotal((weight/100)*vo.getnVeTotal()+(recipeVO.getnVeTotal()==null?0:recipeVO.getnVeTotal()));
            recipeVO.setnCa((weight/100)*vo.getnCa()+(recipeVO.getnCa()==null?0:recipeVO.getnCa()));
            recipeVO.setnP((weight/100)*vo.getnP()+(recipeVO.getnP()==null?0:recipeVO.getnP()));
            recipeVO.setnK((weight/100)*vo.getnK()+(recipeVO.getnK()==null?0:recipeVO.getnK()));
            recipeVO.setnNa((weight/100)*vo.getnNa()+(recipeVO.getnNa()==null?0:recipeVO.getnNa()));
            recipeVO.setnMg((weight/100)*vo.getnMg()+(recipeVO.getnMg()==null?0:recipeVO.getnMg()));
            recipeVO.setnFe((weight/100)*vo.getnFe()+(recipeVO.getnFe()==null?0:recipeVO.getnFe()));
            recipeVO.setnZn((weight/100)*vo.getnZn()+(recipeVO.getnZn()==null?0:recipeVO.getnZn()));
            recipeVO.setnSe((weight/100)*vo.getnSe()+(recipeVO.getnSe()==null?0:recipeVO.getnSe()));
            recipeVO.setnCu((weight/100)*vo.getnCu()+(recipeVO.getnCu()==null?0:recipeVO.getnCu()));
            recipeVO.setnMn((weight/100)*vo.getnMn()+(recipeVO.getnMn()==null?0:recipeVO.getnMn()));
//            recipeVO.setnGi((weight/100)*vo.getnGi()+(recipeVO.getnGi()==null?0:recipeVO.getnGi()));
//            recipeVO.setnSalt((weight/100)*vo.getnSalt()+(recipeVO.getnFat()==null?0:recipeVO.getnFat()));
//            recipeVO.setnSugar((weight/100)*vo.getnSugar()+(recipeVO.getnSugar()==null?0:recipeVO.getnSugar()));
//            recipeVO.setnTotal((weight/100)*vo.getnTotal()+(recipeVO.getnTotal()==null?0:recipeVO.getnTotal()));
        }
    }

}
