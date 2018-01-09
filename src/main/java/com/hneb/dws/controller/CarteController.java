package com.hneb.dws.controller;

import com.hneb.dws.dao.*;
import com.hneb.dws.service.NutrientService;
import com.hneb.dws.service.RecipeService;
import com.hneb.dws.vo.*;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.query.PageQueryTools;
import com.hneb.fwk.query.PageResultDTO;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.BeanUtils;
import com.hneb.fwk.utils.ResultUtils;
import com.hneb.fwk.utils.VoUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */
@RestController
@RequestMapping("/carte")
public class CarteController {

    @Autowired
    private CarteDao carteDao;
    @Autowired
    private CarteListDao carteListDao;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeFoodDao recipeFoodDao;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private TabooDao tabooDao;
    @Autowired
    private NutrientService nutrientService;
    @Autowired
    private PageQueryTools queryTools;

    //查询所有的餐单
    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    public Result queryList(@RequestBody JSONObject params) throws Exception {
        // 处理时间范围
        JSONArray date = params.getJSONArray("dateRange");
        if(date!=null && date.size()>0){
            String begin = date.get(0).toString();
            String end = date.get(1).toString();
            params.put("begin",begin.substring(0,begin.indexOf("T")));
            params.put("end",end.substring(0,begin.indexOf("T")));
        }
        String sql="SELECT " +
                    "   * " +
                    "FROM " +
                    "   t_carte tc " +
                    "WHERE  " +
                    "   tc.C_PHONE_NO LIKE '%#phone%' " +
                    "  AND tc.C_USER_ID = '"+CurrentUser.getUser().getUserId()+"'"+
                    "  AND (tc.T_BGN_TM>='#begin' AND tc.T_END_TM<='#end') "+
                    "  AND tc.C_CHILD_NME LIKE '%#name%' ";
        PageResultDTO resultDTO = queryTools.querySql(sql, params);
        return ResultUtils.success(resultDTO,"查询成功");
    }

    // 查询单个餐单
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject params){
        JSONObject json = new JSONObject();
        // 先是初始化基础数据部分
        String cPkId = params.getString("cPkId");
        CarteVO carte = carteDao.getCarteRepository().getCarteVOByCPkId(cPkId);
        json.put("carte",carte);
        // 具体每天吃什么的数据待确定 //TODO
        json.put("mealData","");
        return ResultUtils.success(json);
    }
    //保存餐单主表
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody JSONObject params) throws Exception {
        JSONArray tTm = params.getJSONArray("tTm");
        params.put("tBgnTm", tTm.get(0));
        params.put("tEndTm", tTm.get(1));
        CarteVO vo =new CarteVO();
        com.hneb.fwk.utils.BeanUtils.map2Bean(params,vo);
        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();
        vo.setcUserId(userId);
        VoUtils.touch(vo,now,userId);

        vo = this.carteDao.getCarteRepository().saveAndFlush(vo);

        return ResultUtils.success(JsonUtils.object2Json(vo));
    }
    // 删除餐单
    @Transactional
    @RequestMapping(value = "/deleteCarte",method = RequestMethod.POST)
    public Result deleteCarte(@RequestBody JSONObject params){
        String c_pk_id = params.getString("C_PK_ID");
        carteDao.getCarteRepository().deleteCarteVOByCPkId(c_pk_id);
        carteListDao.getCarteListRepository().deleteCarteListVOByCCarteId(c_pk_id);
        return ResultUtils.success();
    }

    //保存餐单从表t_carte_list(即餐单中添加菜品）
    @RequestMapping(value = "/addRecipe",method = RequestMethod.POST)
    public Result addRecipe(@RequestBody JSONObject params) throws Exception {
//        cCarteId 餐单id
//        cRecipeId 菜品id
//        cRecipeUserId 菜品创建人id
//        tEatTm 就餐时间
//        cMealMrk 餐别标识
//        cRecipeNme 菜品名称
//        nWeight 菜品重量
//        cDesc 菜品描述

        CarteListVO vo = new CarteListVO();
        BeanUtils.map2Bean(params,vo);
        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();
//        vo.setcUserId(userId);
        VoUtils.touch(vo,now,userId);

        vo = this.carteListDao.getCarteListRepository().saveAndFlush(vo);

        return ResultUtils.success(vo);
    }


    // 删除
    @RequestMapping(value = "/delRecipe",method = RequestMethod.POST)
    public Result delRecipe(@RequestBody JSONObject params) throws Exception {
        String pkId = params.getString("cPkId");//t_carte_list的主键pkId

        CarteListVO carte = this.carteListDao.getCarteListRepository().findByCPkId(pkId);
        RecipeVO recipe = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(carte.getcRecipeId());

        //将菜品从菜单中移除
        this.carteListDao.getCarteListRepository().delete(carte);
        //如果菜品的最新标志不为1，则删除该菜品，避免垃圾数据
        if(!"1".endsWith(recipe.getcLatestMrk())){
            this.recipeDao.getRecipeRep().delete(recipe);
        }

        return ResultUtils.success();
    }

    //修改餐单中某一个菜品的食材重量
    @RequestMapping(value = "/modifyRecipe",method = RequestMethod.POST)
    public Result modifyRecipe(@RequestBody JSONObject params) throws Exception {
        JSONArray foods = params.getJSONArray("foods");
        String recipeId = params.getString("recipeId");//食谱id

        RecipeVO recipe = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(recipeId);
        List<RecipeFoodVO> list = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipe.getcPkId());
        CarteListVO carteListVO=null;
        //如果是最新标志，则需要生成副本再改
        if("1".equals(recipe.getcLatestMrk())){
            recipe = this.recipeService.copyRecipe(recipe);
            list = this.recipeService.copyRecipeFood(list,recipe.getcPkId());
            carteListVO = this.carteListDao.getCarteListRepository().findByCRecipeId(recipe.getcOrgId());
        }else{
            carteListVO = this.carteListDao.getCarteListRepository().findByCRecipeId(recipe.getcPkId());
        }

        recipe.setnWater(0d);
        recipe.setnEnergy1(0d);
        recipe.setnEnergy2(0d);
        recipe.setnProtein(0d);
        recipe.setnFat(0d);
        recipe.setnCho(0d);
        recipe.setnDietaryFiber(0d);
        recipe.setnCholesterol(0d);
        recipe.setnAsh(0d);
        recipe.setnVa(0d);
        recipe.setnCarotene(0d);
        recipe.setnRetinol(0d);
        recipe.setnThiamin(0d);
        recipe.setnRiboflavin(0d);
        recipe.setnNiacin(0d);
        recipe.setnVc(0d);
        recipe.setnCa(0d);
        recipe.setnP(0d);
        recipe.setnK(0d);
        recipe.setnNa(0d);
        recipe.setnMg(0d);
        recipe.setnFe(0d);
        recipe.setnZn(0d);
        recipe.setnSe(0d);
        recipe.setnCu(0d);
        recipe.setnMn(0d);
//        recipe.setnVerecipe(0d);
//        recipe.setnGi(0d);
//        recipe.setnSalt(0d);
//        recipe.setnSugar(0d);
//        recipe.setnrecipe(0d);

        String foodId;
        int weight;
        int totalWeight=0;
        String foodDesc="";
        FoodVO f;
        for(RecipeFoodVO food : list){
            foodId = food.getcFoodId();
            weight = food.getnWeight();

            for(Object tmp:foods){
                if(foodId.endsWith(((JSONObject)tmp).getString("cFoodId"))){
                    food.setnWeight(Integer.parseInt(((JSONObject)tmp).getString("nWeight")));
                    weight = food.getnWeight();
                }
            }

            f = this.foodDao.getFoodRep().getFoodVOByCPkId(foodId);

            recipe.setnWater((weight/100)*f.getnWater()+recipe.getnWater());
            recipe.setnEnergy1((weight/100)*f.getnEnergy1()+recipe.getnEnergy1());
            recipe.setnEnergy2((weight/100)*f.getnEnergy2()+recipe.getnEnergy2());
            recipe.setnProtein((weight/100)*f.getnProtein()+recipe.getnProtein());
            recipe.setnFat((weight/100)*f.getnFat()+recipe.getnFat());
            recipe.setnCho((weight/100)*f.getnCho()+recipe.getnCho());
            recipe.setnDietaryFiber((weight/100)*f.getnDietaryFiber()+recipe.getnDietaryFiber());
            recipe.setnCholesterol((weight/100)*f.getnCholesterol()+recipe.getnCholesterol());
            recipe.setnAsh((weight/100)*f.getnAsh()+recipe.getnAsh());
            recipe.setnVa((weight/100)*f.getnVa()+recipe.getnVa());
            recipe.setnCarotene((weight/100)*f.getnCarotene()+recipe.getnCarotene());
            recipe.setnRetinol((weight/100)*f.getnRetinol()+recipe.getnRetinol());
            recipe.setnThiamin((weight/100)*f.getnThiamin()+recipe.getnThiamin());
            recipe.setnRiboflavin((weight/100)*f.getnRiboflavin()+recipe.getnRiboflavin());
            recipe.setnNiacin((weight/100)*f.getnNiacin()+recipe.getnNiacin());
            recipe.setnVc((weight/100)*f.getnVc()+recipe.getnVc());

            recipe.setnCa((weight/100)*f.getnCa()+recipe.getnCa());
            recipe.setnP((weight/100)*f.getnP()+recipe.getnP());
            recipe.setnK((weight/100)*f.getnK()+recipe.getnK());
            recipe.setnNa((weight/100)*f.getnNa()+recipe.getnNa());
            recipe.setnMg((weight/100)*f.getnMg()+recipe.getnMg());
            recipe.setnFe((weight/100)*f.getnFe()+recipe.getnFe());
            recipe.setnZn((weight/100)*f.getnZn()+recipe.getnZn());
            recipe.setnSe((weight/100)*f.getnSe()+recipe.getnSe());
            recipe.setnCu((weight/100)*f.getnCu()+recipe.getnCu());
            recipe.setnMn((weight/100)*f.getnMn()+recipe.getnMn());
//            recipe.setnVerecipe((weight/100)*f.getnVerecipe()+recipe.getnVerecipe());
//            recipe.setnGi((weight/100)*f.getnGi()+recipe.getnGi());
//            recipe.setnSalt((weight/100)*f.getnSalt()+recipe.getnFat());
//            recipe.setnSugar((weight/100)*f.getnSugar()+recipe.getnSugar());
//            recipe.setnrecipe((weight/100)*f.getnrecipe()+recipe.getnrecipe());
            totalWeight+=weight;
            foodDesc+=food.getcFoodNme()+" "+weight+" ";
        }
        // 更新总重量和食材的重量
        recipe.setcFoodDesc(foodDesc);
        recipe.setnWeight(totalWeight);

        recipe = this.recipeDao.getRecipeRep().save(recipe);
        this.recipeFoodDao.getRecipeFoodRep().save(list);
        // 更改cart_list中的数据
        carteListVO.setcRecipeId(recipe.getcPkId());
        carteListVO.setnWeight(recipe.getnWeight());
        carteListVO.setcDesc(recipe.getcFoodDesc());
        this.carteListDao.getCarteListRepository().saveAndFlush(carteListVO);
        return ResultUtils.success(recipe);
    }


    /**
     * 侦测是否含有特殊禁忌食物
     * @param params
     * @return
     */
    @RequestMapping(value = "/detectTaboo",method = RequestMethod.POST)
    public Result detectTaboo(@RequestBody JSONObject params) throws Exception {
        JSONObject result = new JSONObject();
        result.put("mrk","0");//0未触发禁忌规则，1，触发禁忌规则
        result.put("msg","");//返回到前台提示的信息
        String recipeId = params.getString("recipeId");
        JSONArray taboo = (JSONArray) params.get("taboo");
        JSONArray speTaboo = (JSONArray)params.get("speTaboo");

        if((taboo==null||taboo.size()==0 )&& (speTaboo==null||speTaboo.size()==0)){
            return ResultUtils.success(result);
        }

        String tabooFoods="";
        if(taboo!=null&&taboo.size()>0){
            List tabooFoodsList = this.tabooDao.getTabooFoods(taboo);
            if(tabooFoodsList!=null&&tabooFoodsList.size()>0){
                tabooFoods+=tabooFoodsList.get(0);
            }
        }
        if(speTaboo!=null&&speTaboo.size()>0){
            tabooFoods += ";"+speTaboo;
        }

        String tabooFoodsNme = "";
        List<RecipeFoodVO> foods = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipeId);
        for(RecipeFoodVO food:foods){
            if(tabooFoods.indexOf(food.getcFoodId())!=-1){
                tabooFoodsNme +=","+food.getcFoodNme();
            }
        }
        if(!"".equals(tabooFoodsNme)){
            result.put("mrk",'1');
            result.put("msg","菜品中含禁忌食物:"+tabooFoodsNme.substring(1));
            return ResultUtils.success(result);
        }else{
            return ResultUtils.success(result);
        }
    }


    /**
     * 侦测是否含有特殊禁忌食物
     * @param params
     * @return
     */
    @RequestMapping(value = "/calc",method = RequestMethod.POST)
    public Result calc(@RequestBody JSONObject params) throws Exception {
        String std = params.getString("std");//适用营养素标准
        String sex = params.getString("sex");//性别
        String carteId = params.getString("carteId");
        String dateId = params.getString("dateId");
        Float age=0.0f;
        if(std.indexOf("-")!=-1)
            age = Float.parseFloat(std.split("-")[0]);

        String type = "2";
        if(age>=6){
            type = std.split("-")[1];
        }

        NutrientService.NutrientBo nutrientBo = this.nutrientService.get(sex,type,age);
        Map<String,String> energy = this.nutrientService.getEnergy(age,nutrientBo.getProtein(),nutrientBo.getEnergy());
        Map<String,Double> sum = this.recipeService.sum(carteId,dateId);
        Map<String,Double> result = this.nutrientService.calcResult(nutrientBo,energy,sum);

        JSONObject json = new JSONObject();
        json.put("bo",JsonUtils.object2Json(nutrientBo));
        json.put("energy",JsonUtils.object2Json(energy));
        json.put("sum",JsonUtils.object2Json(sum));
        json.put("result",JsonUtils.object2Json(result));

        return ResultUtils.success(json);


    }


}
