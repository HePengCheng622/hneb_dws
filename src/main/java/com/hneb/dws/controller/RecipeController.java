package com.hneb.dws.controller;


import com.hneb.ResultEnum;
import com.hneb.dws.config.SysConfig;
import com.hneb.dws.dao.FoodDao;
import com.hneb.dws.dao.RecipeDao;
import com.hneb.dws.dao.RecipeFoodDao;
import com.hneb.dws.service.RecipeService;
import com.hneb.dws.vo.RecipeFoodVO;
import com.hneb.dws.vo.RecipeVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.exception.HnebException;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.query.PageQueryTools;
import com.hneb.fwk.query.PageResultDTO;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.BeanUtils;
import com.hneb.fwk.utils.ResultUtils;
import com.hneb.fwk.utils.VoUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author HPC
 * @create 2017-12-06 10:07
 * @desc 菜单控制类
 **/
@RestController
@RequestMapping("/recipe")
@Configuration
@EnableConfigurationProperties(SysConfig.class)
public class RecipeController {

    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private RecipeFoodDao recipeFoodDao;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private PageQueryTools queryTools;
    @Autowired
    private RecipeService recipeService;


    /**
     * 保存菜谱主表
     * @param params
     * @return 注意返回值，不同的业务情况返回不一样
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody JSONObject params) throws Exception{
        //将主表数据转换为对象
        RecipeVO recipeVO = new RecipeVO();
        BeanUtils.map2Bean(params.getJSONObject("form"),recipeVO);

        //设置默认值，否则转json到前台时会报错
        this.recipeService.setDefualtValue(recipeVO);

        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();
        String id = UUID.randomUUID().toString();
        JSONObject resultJson = new JSONObject();
        if ("".equals(recipeVO.getcPkId()) || recipeVO.getcPkId() == null) {
            recipeVO.setcPkId(id);
            recipeVO.setcOrgId(id);// TODO 待定
            recipeVO.setcUserId(userId);// 传过来的userId
            recipeVO.setcLatestMrk("1");

            VoUtils.touchOnCreate(recipeVO,now,userId);

            recipeDao.getRecipeRep().saveAndFlush(recipeVO);
            resultJson.put("recipe",JsonUtils.object2Json(recipeVO));
            return ResultUtils.success(resultJson);
        } else {
            //判断recipeVO.getcPkId是否在t_carte_list中存在数据
            int num = this.recipeDao.countRecipe(recipeVO.getcPkId());
            //如果不存在，直接修改
            if(num==0){
                VoUtils.touch(recipeVO,now,userId);
                recipeDao.getRecipeRep().saveAndFlush(recipeVO);
                return ResultUtils.success();
            }
            //如果存在，则拷贝recipeVO和recipeFoodVo为副本，并保存。
            // 保存前需将老的recipeVO的最新标志至为0，副本的最新标志为1. orgId
            // 且需将最新的数据状态传递到前台，尤其是pkId
            else{
                String oldRecipeId = recipeVO.getcPkId();
                String orgId = recipeVO.getcOrgId();

                String newRecipeId = UUID.randomUUID().toString();


                //获取原菜单中食材列表
                List<RecipeFoodVO> oldRecipeFood = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(oldRecipeId);
                //copy原食材列表
                List<RecipeFoodVO> newRecipeFood = this.recipeService.copyRecipeFood(oldRecipeFood,newRecipeId);
                //前台传递过来的对象给个新的pkId数据库就会执行新增操作
                recipeVO.setcPkId(newRecipeId);
                //置最新标志
                recipeVO.setcLatestMrk("1");

                //营养值和食材描述都不需要重新生成，应该找个保存方法前台只改了recipeVO中的数据。recipeFoodVO中的增、删、改才需要重新生成食材描述和计算营养值

                this.recipeDao.getRecipeRep().save(recipeVO);
                this.recipeFoodDao.getRecipeFoodRep().save(newRecipeFood);
                //更新原菜谱的最新标志为否
                this.recipeDao.updateRecipeLatestMrk(oldRecipeId,"0");


                resultJson.put("recipe",JsonUtils.object2Json(recipeVO));
                resultJson.put("foods",JsonUtils.listObject2Json(newRecipeFood));
                //前台需要将这两个业务对象的数据重新回填，因为pkId变了
                return ResultUtils.success(resultJson);
            }
        }


    }

    /**
     * 添加食材
     * @param json
     * @return
     */
    @RequestMapping(value = "/addFood",method = RequestMethod.POST)
    public Result addFood(@RequestBody JSONObject json) throws Exception {
        //1、判断该recipeId是否被t_carte_list引用
        //  1、1如果没有，则直接添加
        //  1、2 如果存在，则拷贝recipeVO和recipeFoodVo为副本，并保存。
        //       保存前需将老的recipeVO的最新标志至为0，副本的最新标志为1. orgId
        //       需将新增的是食材保存到recipeFoodVO中
        //       且需将最新的数据状态传递到前台，尤其是pkId
        JSONObject resultJson = new JSONObject();

        RecipeFoodVO recipeFoodVO = new RecipeFoodVO();
        BeanUtils.map2Bean(json,recipeFoodVO);


        String userId = CurrentUser.getUser().getUserId();
        Date now = new Date();

        //判断recipeVO.getcPkId是否在t_carte_list中存在数据
        int num = this.recipeDao.countRecipe(recipeFoodVO.getcRecipeId());
        RecipeVO recipeVO = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(recipeFoodVO.getcRecipeId());
        List<RecipeFoodVO> list = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipeFoodVO.getcRecipeId());
        if(num==0){
            recipeFoodVO.setcUserId(userId);
            VoUtils.touchOnCreate(recipeFoodVO,now,userId);
            VoUtils.touch(recipeVO,now,userId);
            //更新食材描述（因为是新增食材，所以只需要在原描述后追加一个描述即可）
            recipeVO.setcFoodDesc((recipeVO.getcFoodDesc()==null?"":(recipeVO.getcFoodDesc()+" , "))+recipeFoodVO.getcFoodNme()+recipeFoodVO.getnWeight()+"克");
            //更新重量（因为是新增食材，所以只需要在原重量上加上新食材的重量即可）
            recipeVO.setnWeight(recipeVO.getnWeight()+recipeFoodVO.getnWeight());
            //计算营养值
            list.add(recipeFoodVO);
            this.recipeService.calcNutrient(recipeVO,list);

            //保存主表
            this.recipeDao.getRecipeRep().save(recipeVO);
            //保存菜品的食材表
            recipeFoodVO = this.recipeFoodDao.getRecipeFoodRep().save(recipeFoodVO);

            resultJson.put("recipe",JsonUtils.object2Json(recipeVO));
            resultJson.put("foods",JsonUtils.listObject2Json(list));
            return ResultUtils.success(resultJson);
        }else{
            RecipeVO newRecipe = this.recipeService.copyRecipe(recipeVO);
            List<RecipeFoodVO> newList = this.recipeService.copyRecipeFood(list,newRecipe.getcPkId());

            newRecipe.setcLatestMrk("1");
            //更新食材描述（因为是新增食材，所以只需要在原描述后追加一个描述即可）
            newRecipe.setcFoodDesc(newRecipe.getcFoodDesc()+","+recipeFoodVO.getcFoodNme()+recipeFoodVO.getnWeight());
            //更新重量（因为是新增食材，所以只需要在原重量上加上新食材的重量即可）
            newRecipe.setnWeight(newRecipe.getnWeight()+recipeFoodVO.getnWeight());

            recipeFoodVO.setcUserId(userId);
            recipeFoodVO.setcRecipeId(newRecipe.getcPkId());
            //计算营养成分
            newList.add(recipeFoodVO);

            VoUtils.touchOnCreate(recipeFoodVO,now,userId);
            VoUtils.touchOnCreate(newRecipe,now,userId);
            this.recipeService.calcNutrient(newRecipe,newList);

            //保存主表
            this.recipeDao.getRecipeRep().save(newRecipe);
            //保存菜品的食材表
            recipeFoodVO = this.recipeFoodDao.getRecipeFoodRep().save(recipeFoodVO);
            //将原菜品最新标志置为否0
            this.recipeDao.updateRecipeLatestMrk(recipeVO.getcPkId(),"0");

            resultJson.put("recipe",newRecipe);
            resultJson.put("foods",JsonUtils.listObject2Json(newList));

            return ResultUtils.success(resultJson);
        }
    }

    /**
     * 修改菜单中的食材重量
     * @param json
     * @return
     */
    @RequestMapping(value = "/editFood",method = RequestMethod.POST)
    public Result editFood(@RequestBody JSONObject json) throws Exception {
        JSONObject resultJson = new JSONObject();

        String recipeId = json.getString("recipeId");//食谱的主键。即t_recipe.c_pk_id
        String pkId = json.getString("pkId");//食谱中食材的主键。即t_recipe_food.c_pk_id
        int weight = json.getInt("weight");//修改后的重量


        RecipeVO recipeVO = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(recipeId);
        List<RecipeFoodVO> list = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipeId);
        int count = this.recipeDao.countRecipe(recipeId);
        //没有被其他餐单引用过，直接更新
        int newWeight=0;
        if(count==0) {
            for (RecipeFoodVO vo : list) {
                if (pkId.equals(vo.getcFoodId())) {
                    vo.setnWeight(weight);
                    this.recipeFoodDao.getRecipeFoodRep().saveAndFlush(vo);
                }
                newWeight = newWeight + vo.getnWeight();
            }
            //更新营养素
            this.recipeService.calcNutrient(recipeVO, list);
            //更新重量
            recipeVO.setnWeight(newWeight);
            //更新食材描述
            recipeVO.setcFoodDesc(this.recipeService.generateDesc(list));
            //保存主表
            this.recipeDao.getRecipeRep().save(recipeVO);

            //将主表数据返回到前台，因为营养素和重量都发生了变化
            resultJson.put("recipe", JsonUtils.object2Json(recipeVO));
            return ResultUtils.success(resultJson);
        }
        else{
            RecipeVO newRecipe = this.recipeService.copyRecipe(recipeVO);
            List<RecipeFoodVO> newList = this.recipeService.copyRecipeFood(list,newRecipe.getcPkId());

            for (RecipeFoodVO vo : newList) {
                if (pkId.equals(vo.getcFoodId())) {
                    vo.setnWeight(weight);
                }
                vo.setcRecipeId(newRecipe.getcPkId());
                newWeight = newWeight + vo.getnWeight();
            }

            //将原菜品最新标志置为否0
            this.recipeDao.updateRecipeLatestMrk(recipeId,"0");
            //置最新标志为1
            newRecipe.setcLatestMrk("1");
            //更新营养素
            this.recipeService.calcNutrient(newRecipe, newList);
            //更新重量
            newRecipe.setnWeight(newWeight);
            //更新食材描述
            newRecipe.setcFoodDesc(this.recipeService.generateDesc(newList));
            //保存主表
            newRecipe = this.recipeDao.getRecipeRep().save(newRecipe);
            //保存从表数据
            newList = this.recipeFoodDao.getRecipeFoodRep().save(newList);

            resultJson.put("recipe",JsonUtils.object2Json(newRecipe));
            resultJson.put("foods",JsonUtils.listObject2Json(newList));

            return ResultUtils.success(resultJson);
        }



        //1、判断该recipeId是否被t_carte_list引用
        //  1、1如果没有，则直接添加
        //  1、2 如果存在，则拷贝recipeVO和recipeFoodVo为副本，并保存。
        //       保存前需将老的recipeVO的最新标志至为0，副本的最新标志为1. orgId
        //       需将新增的是食材保存到recipeFoodVO中
        //       且需将最新的数据状态传递到前台，尤其是pkId
    }





    /**
     * 根据pkId删除食谱中的指定食材
     * @param json
     * @return
     */
    @RequestMapping(value = "/deleteFood",method = RequestMethod.POST)
    @Transactional
    public Result deleteFood(@RequestBody JSONObject json) throws Exception {
//        String cPkId = json.getString("cPkId");
//        String cFoodId = json.getString("cFoodId");
//        recipeFoodDao.getRecipeFoodRep().deleteRecipeFoodVOByCPkIdAndCFoodId(cPkId,cFoodId);
//        return ResultUtils.success();

        JSONObject resultJson = new JSONObject();

        String recipeId = json.getString("recipeId");//食谱的主键。即t_recipe.c_pk_id
        String pkId = json.getString("pkId");//食谱中食材的主键。即t_recipe_food.c_pk_id

        RecipeVO recipeVO = this.recipeDao.getRecipeRep().getRecipeVOByCPkId(recipeId);
        List<RecipeFoodVO> list = this.recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipeId);
        int count = this.recipeDao.countRecipe(recipeId);
        int foodWeight = 0;
        int foodIndex = 0;
        //没有被其他餐单引用过，直接删除
        if(count==0) {
            for (int i=0;i<list.size();i++) {
                RecipeFoodVO vo = list.get(i);
                if (pkId.equals(vo.getcPkId())) {
                    foodWeight = vo.getnWeight();
                    this.recipeFoodDao.getRecipeFoodRep().delete(vo);
                    foodIndex = i;
                }
            }
            list.remove(foodIndex);
            //更新营养素
            this.recipeService.calcNutrient(recipeVO, list);
            //更新重量
            recipeVO.setnWeight(recipeVO.getnWeight()-foodWeight);
            //更新食材描述
            recipeVO.setcFoodDesc(this.recipeService.generateDesc(list));
            //保存主表
            this.recipeDao.getRecipeRep().save(recipeVO);

            //将主表数据返回到前台，因为营养素和重量都发生了变化
            resultJson.put("recipe", JsonUtils.object2Json(recipeVO));
            return ResultUtils.success(resultJson);
        }
        else{


            String newPkId = UUID.randomUUID().toString();

            for (int i=0;i<list.size();i++) {
                RecipeFoodVO vo = list.get(i);
                if (pkId.equals(vo.getcPkId())) {
                    foodWeight = vo.getnWeight();
                    foodIndex = i;
                }
            }
            list.remove(foodIndex);

            RecipeVO newRecipe = this.recipeService.copyRecipe(recipeVO);
            List<RecipeFoodVO> newList = this.recipeService.copyRecipeFood(list,newPkId);

            //置最新标志为1
            newRecipe.setcLatestMrk("1");
            //更新营养素
            this.recipeService.calcNutrient(newRecipe, newList);
            //更新重量
            recipeVO.setnWeight(recipeVO.getnWeight()-foodWeight);
            //更新食材描述
            recipeVO.setcFoodDesc(this.recipeService.generateDesc(newList));
            //保存主表
            newRecipe = this.recipeDao.getRecipeRep().save(newRecipe);
            //保存从表数据
            newList = this.recipeFoodDao.getRecipeFoodRep().save(newList);
            //将原菜品最新标志置为否0
            this.recipeDao.updateRecipeLatestMrk(recipeId,"0");

            resultJson.put("recipe",JsonUtils.object2Json(newRecipe));
            resultJson.put("foods",JsonUtils.listObject2Json(newList));

            return ResultUtils.success(resultJson);
        }
    }


    /**
     * 获取菜谱
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Result detail(@RequestBody JSONObject json) {
        // 获取所有的recipe
        RecipeVO recipeVO = recipeDao.getRecipeRep().getRecipeVOByCPkId(json.getString("cPkId"));
        List<RecipeFoodVO> list = recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(json.getString("cPkId"));
        // 以JSON对象返回到前段
        JSONObject obj = new JSONObject();
        obj.put("recipe", JsonUtils.object2Json(recipeVO));
        obj.put("foods",JsonUtils.listObject2JsonArray(list));
        obj.put("userId",CurrentUser.getUser().getUserId());
        return ResultUtils.success(obj);
    }


    /**
     * 食谱的副本
     * @param json
     * @return
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public Result copy(@RequestBody JSONObject json) {
        String pkId= json.getString("pkId");
        String name= json.getString("name");
        String newPkId = this.recipeService.copy(pkId);
        JSONObject result = new JSONObject();
        result.put("pkId",newPkId);
        result.put("name",name+"_副本");
        return ResultUtils.success(result);
    }

    /**
     * 删除食谱（食谱列表中的按钮点击方法）
     * @param json
     * @return
     */
    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody JSONObject json) throws Exception {
        String pkId= json.getString("pkId");
        String userId= json.getString("userId");
        //如果是直接copy的（且前台没有重新执行查询） 则前台传递过来的该菜品的userId为空字符串
        if(!"".equals(userId)&&!CurrentUser.getUser().getUserId().equals(userId)){
            throw new HnebException(ResultEnum.RECIPE_NO_DELETE_AUTH);
        }
        int count = this.recipeDao.countRecipe(pkId);
        //如果食谱没有引用直接删除，如果有引用设置最新标志为否0
        if(count==0){
            this.recipeDao.getRecipeRep().deleteByCPkId(pkId);
            this.recipeFoodDao.getRecipeFoodRep().deleteByCRecipeId(pkId);
        }else{
            this.recipeDao.updateRecipeLatestMrk(pkId,"0");
        }
        return ResultUtils.success("删除成功");
    }



    /**
     * 获取所有的菜谱
     *
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject json) throws Exception {
        String userIds="";
        if(json.getJSONArray("cTags").size()>0){
            String cTags = json.getJSONArray("cTags").toString();
            json.put("cTags",cTags.substring(1,cTags.length()-1));
        }
        if(json.getJSONArray("cTypes").size()>0){
            String cTypes = json.getJSONArray("cTypes").toString();
            json.put("cTypes",cTypes.substring(1,cTypes.length()-1));
        }
        String adminId = sysConfig.getAdmin_id();
        if("0".equals(json.getString("cMrk"))){
            userIds= "'"+adminId+"','"+ CurrentUser.getUser().getUserId()+"'";
        }else if("1".equals(json.getString("cMrk"))){
            userIds= "'"+adminId+"'";
        }else if("2".equals(json.getString("cMrk"))){
            userIds= "'"+CurrentUser.getUser().getUserId()+"'";
        }
        String sql ="SELECT " +
                    "   (select c_user_nme from t_user b where b.c_user_id = tr.c_user_id) as C_USER_NME," +
                    "   tr.* " +
                    "FROM " +
                    "   t_recipe tr " +
                    "WHERE " +
                    "   tr.C_TAGS LIKE '%#cTags%' " +
                    "   AND tr.C_TYPES LIKE '%#cTypes%' " +
                    "   AND tr.C_NAME LIKE '%#cName%' " +
                    "   AND tr.C_LATEST_MRK='1' " +
                    "   AND tr.C_USER_ID IN ("+userIds+")";
        // 如果获取出来的时间不等于空就进行截取数据
        PageResultDTO resultDTO = queryTools.querySql(sql, json);
        return ResultUtils.success(resultDTO, "查询成功");
    }
//
//    /**
//     * 计算所有食材的能量
//     * @param json
//     * @return
//     */
//    @RequestMapping(value = "/calculateNutrition", method = RequestMethod.POST)
//    public Result calculateNutrition(@RequestBody JSONObject json) {
//        FoodVO total = new FoodVO();
//        for (Object obj : json.getJSONArray("foods")) {
//            String foodId = ((JSONObject) obj).getString("cFoodId");
//            double weight = Double.parseDouble(((JSONObject) obj).getString("nWeight"));
//            FoodVO vo = foodDao.getFoodRep().getFoodVOByCPkId(foodId);
//            total.setnWater((weight/100)*vo.getnWater()+(total.getnWater()==null?0:total.getnWater()));
//            total.setnEnergy1((weight/100)*vo.getnEnergy1()+(total.getnEnergy1()==null?0:total.getnEnergy1()));
//            total.setnEnergy2((weight/100)*vo.getnEnergy2()+(total.getnEnergy2()==null?0:total.getnEnergy2()));
//            total.setnProtein((weight/100)*vo.getnProtein()+(total.getnProtein()==null?0:total.getnProtein()));
//            total.setnFat((weight/100)*vo.getnFat()+(total.getnFat()==null?0:total.getnFat()));
//            total.setnCho((weight/100)*vo.getnCho()+(total.getnCho()==null?0:total.getnCho()));
//            total.setnDietaryFiber((weight/100)*vo.getnDietaryFiber()+(total.getnDietaryFiber()==null?0:total.getnDietaryFiber()));
//            total.setnCholesterol((weight/100)*vo.getnCholesterol()+(total.getnCholesterol()==null?0:total.getnCholesterol()));
//            total.setnAsh((weight/100)*vo.getnAsh()+(total.getnAsh()==null?0:total.getnAsh()));
//            total.setnVa((weight/100)*vo.getnVa()+(total.getnVa()==null?0:total.getnVa()));
//            total.setnCarotene((weight/100)*vo.getnCarotene()+(total.getnCarotene()==null?0:total.getnCarotene()));
//            total.setnRetinol((weight/100)*vo.getnRetinol()+(total.getnRetinol()==null?0:total.getnRetinol()));
//            total.setnThiamin((weight/100)*vo.getnThiamin()+(total.getnThiamin()==null?0:total.getnThiamin()));
//            total.setnRiboflavin((weight/100)*vo.getnRiboflavin()+(total.getnRiboflavin()==null?0:total.getnRiboflavin()));
//            total.setnNiacin((weight/100)*vo.getnNiacin()+(total.getnNiacin()==null?0:total.getnNiacin()));
//            total.setnVc((weight/100)*vo.getnVc()+(total.getnVc()==null?0:total.getnVc()));
//            total.setnVeTotal((weight/100)*vo.getnVeTotal()+(total.getnVeTotal()==null?0:total.getnVeTotal()));
//            total.setnCa((weight/100)*vo.getnCa()+(total.getnCa()==null?0:total.getnCa()));
//            total.setnP((weight/100)*vo.getnP()+(total.getnP()==null?0:total.getnP()));
//            total.setnK((weight/100)*vo.getnK()+(total.getnK()==null?0:total.getnK()));
//            total.setnNa((weight/100)*vo.getnNa()+(total.getnNa()==null?0:total.getnNa()));
//            total.setnMg((weight/100)*vo.getnMg()+(total.getnMg()==null?0:total.getnMg()));
//            total.setnFe((weight/100)*vo.getnFe()+(total.getnFe()==null?0:total.getnFe()));
//            total.setnZn((weight/100)*vo.getnZn()+(total.getnZn()==null?0:total.getnZn()));
//            total.setnSe((weight/100)*vo.getnSe()+(total.getnSe()==null?0:total.getnSe()));
//            total.setnCu((weight/100)*vo.getnCu()+(total.getnCu()==null?0:total.getnCu()));
//            total.setnMn((weight/100)*vo.getnMn()+(total.getnMn()==null?0:total.getnMn()));
//            total.setnGi((weight/100)*vo.getnGi()+(total.getnGi()==null?0:total.getnGi()));
//            total.setnSalt((weight/100)*vo.getnSalt()+(total.getnFat()==null?0:total.getnFat()));
//            total.setnSugar((weight/100)*vo.getnSugar()+(total.getnSugar()==null?0:total.getnSugar()));
//            total.setnTotal((weight/100)*vo.getnTotal()+(total.getnTotal()==null?0:total.getnTotal()));
//        }
//        return ResultUtils.success(total);
//    }
}
