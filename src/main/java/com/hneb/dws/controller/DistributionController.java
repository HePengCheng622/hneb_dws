package com.hneb.dws.controller;

import com.hneb.dws.dao.RecipeDao;
import com.hneb.dws.service.CarteService;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.utils.DateUtils;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25.
 */
@RestController
@RequestMapping("/distribution")
public class DistributionController {

    @Autowired
    private CarteService carteService;
    @Autowired
    private RecipeDao recipeDao;

    /**
     * 配送清单
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result query(@RequestBody JSONObject params) throws Exception {
        String eatTm = params.getString("eatTm");
        String mealMrk = params.getString("mealMrk");
        List<Map> list = this.carteService.getCarte(eatTm,mealMrk);

        String carteId,bgnTm;
        long dayNum;
        List<Map> tmp;
        List<Map> result = new ArrayList<Map>();
        for(Map map : list){
            carteId = map.get("carteId").toString();
            bgnTm = map.get("bgnTm").toString();

            dayNum = (DateUtils.strToDate(bgnTm).getTime()-DateUtils.strToDate(eatTm).getTime())/(24*60*60*1000);
            dayNum = dayNum+1;

            tmp = this.recipeDao.getRecipeAndAddr(carteId,dayNum);

            merge(result,tmp);
        }


        return ResultUtils.success(result);
    }

    private void merge(List<Map> result,List<Map> tmp){
        String addrId1,recipeNme1,foodDesc;
        String addrId2;

        Map m = new HashMap();
        List l = new ArrayList();
        Map addr = new HashMap();
        for(int i=0;i<tmp.size();i++){
            if(i==0){
                addr.putAll(tmp.get(0));
                addr.remove("foodDesc");
                addr.remove("recipeNme");
                m.put("addr",addr);
            }
            tmp.get(i).remove("addr");
            tmp.get(i).remove("addrId");
            tmp.get(i).remove("city");
            tmp.get(i).remove("nme");
            tmp.get(i).remove("phoneNo");
            tmp.get(i).remove("province");
            tmp.get(i).remove("zone");
            l.add(tmp.get(i));
        }
        m.put("recipe",l);
        result.add(m);
//        for(Map m1 : tmp){
//            addrId1 = m1.get("addrId").toString();
//            recipeNme1 = m1.get("recipeNme").toString();
//            boolean merged = false;
//            for(Map m2: result){
//                addrId2 = m2.get("addrId").toString();
//
//                if(addrId2.equals(addrId1)){
//                    m2.put("recipeNme",m2.get("recipeNme").toString()+","+recipeNme1);
//                    merged = true;
//                    break;
//                }
//            }
//            if(!merged){
//                result.add(m1);
//            }
//        }
    }
}
