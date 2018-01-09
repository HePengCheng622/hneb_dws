package com.hneb.dws.controller;

import com.hneb.dws.dao.CarteListDao;
import com.hneb.dws.dao.RecipeFoodDao;
import com.hneb.dws.service.CarteService;
import com.hneb.dws.vo.CarteListVO;
import com.hneb.dws.vo.RecipeFoodVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.utils.DateUtils;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/22.
 */
@RestController
@RequestMapping("/procure")
public class ProcureController {

    @Autowired
    private CarteService carteService;
    @Autowired
    private RecipeFoodDao recipeFoodDao;

    /**
     * 获取食材采购清单
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject params) throws Exception {
        String eatTm = params.getString("eatTm");
        String mealMrk = params.getString("mealMrk");
        List<Map> list = this.carteService.getCarte(eatTm,mealMrk);

        String carteId,bgnTm;
        long dayNum;
        List<Map> tmp;
        Map<String,Integer> result = new HashMap<String,Integer>();
        int weight;
        String foodNme;
        for(Map map : list){
            carteId = map.get("carteId").toString();
            bgnTm = map.get("bgnTm").toString();

            dayNum = (DateUtils.strToDate(bgnTm).getTime()-DateUtils.strToDate(eatTm).getTime())/(24*60*60*1000);
            dayNum = dayNum+1;

            tmp = this.recipeFoodDao.getFood(carteId,mealMrk,dayNum);
            for(Map m : tmp){
                foodNme = m.get("foodNme").toString();
                weight = Integer.parseInt(m.get("weight").toString());

                if(result.get(foodNme)!=null){
                    result.put(foodNme,result.get(foodNme)+weight);
                }else{
                    result.put(foodNme,weight);
                }
            }
        }

        return ResultUtils.success(result);
    }


}
