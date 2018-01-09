package com.hneb.dws.controller;

import com.hneb.dws.dao.RecipeFoodDao;
import com.hneb.dws.vo.RecipeFoodVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.ResultUtils;
import com.qiniu.util.Json;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author HPC
 * @create 2017-12-06 10:04
 * @desc 菜单食材控制类
 **/
@RestController
@RequestMapping("/recipeFood")
public class RecipeFoodController {

    @Autowired
    private RecipeFoodDao recipeFoodDao;

    /**
     * 获取菜单中的所有食材
     *
     * @param json
     */
    @RequestMapping(value = "/fetchRecipeFood", method = RequestMethod.POST)
    public Result fetchRecipeFood(@RequestBody JSONObject json) {
        // 根据菜单的id获取当前的菜单所需要的食材
        String recipeId = json.getString("recipeId");
        // 查询所有的食材
        List list = recipeFoodDao.getRecipeFoodRep().getRecipeFoodVOByCRecipeId(recipeId);
        JSONObject obj = new JSONObject();
        // 转为json传给前段
        obj.put("recipeFoods", list);
        return ResultUtils.success(obj);
    }

//    /**
//     * 保存或者更新食谱食材
//     *
//     * @param json
//     * @return
//     */
//    @RequestMapping(value = "/saveOrUpdateRecipeFood", method = RequestMethod.POST)
//    public Result saveOrUpdateRecipeFood(@RequestBody JSONObject json) {
//        // 将json转换为实体对象
//        RecipeFoodVO recipeFoodVO  = (RecipeFoodVO) JSONObject.toBean(json, new RecipeFoodVO(), JsonUtils.getJsonConfig());
//        // 如果pkId为空的话说明是新添加的，手动添加创建时间和创建人
//        if("".equals(recipeFoodVO.getcPkId())){
//            recipeFoodVO.settCrtTm(new Date());
//            recipeFoodVO.setcCrtId(CurrentUser.getUser().getUserId());
//        }
//        recipeFoodVO.settUpdTm(new Date());
//        recipeFoodVO.setcUpdId(CurrentUser.getUser().getUserId());
//        // 将它更新到数据库中去
//        recipeFoodDao.getRecipeFoodRep().saveAndFlush(recipeFoodVO);
//        return ResultUtils.success();
//    }

    /**
     * 根据pkId删除食材
     * @param json
     * @return
     */
    @RequestMapping(value = "/deleteRecipeFood",method = RequestMethod.POST)
    @Transactional
    public Result deleteRecipeFood(@RequestBody JSONObject json){
        String cPkId = json.getString("cPkId");
        String cFoodId = json.getString("cFoodId");
        recipeFoodDao.getRecipeFoodRep().deleteRecipeFoodVOByCPkIdAndCFoodId(cPkId,cFoodId);
        return ResultUtils.success();
    }
}
