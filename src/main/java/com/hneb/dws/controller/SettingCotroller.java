package com.hneb.dws.controller;

import com.hneb.dws.dao.SettingDao;
import com.hneb.dws.dao.SvcPriceDao;
import com.hneb.dws.vo.SettingVO;
import com.hneb.dws.vo.SvcPriceVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.json.JsonUtils;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
@RestController
@RequestMapping("/setting")
public class SettingCotroller {

    @Autowired
    private SettingDao settingDao;
    @Autowired
    private SvcPriceDao svcPriceDao;

    /**
     * 获取当前用户的设置数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public Result details() throws Exception {
        String userId = CurrentUser.getUser().getUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("setting", JsonUtils.object2Json(settingDao.getSettingRep().getByCUserId(userId)));
        jsonObject.put("twprice", JsonUtils.object2Json(svcPriceDao.getPriceRep().getByCSalerIdAndCProdType(userId, "1")));
        jsonObject.put("dhprice", JsonUtils.object2Json(svcPriceDao.getPriceRep().getByCSalerIdAndCProdType(userId, "2")));
        return ResultUtils.success(jsonObject, "查询数据成功");
    }

    /**
     * 保存设置数据
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public Result saveData(@RequestBody JSONObject json) {
        SettingVO setting = (SettingVO) JSONObject.toBean(json.getJSONObject("setting"), SettingVO.class);
        SvcPriceVO twprice = (SvcPriceVO) JSONObject.toBean(json.getJSONObject("twprice"), SvcPriceVO.class);
        SvcPriceVO dhprice = (SvcPriceVO) JSONObject.toBean(json.getJSONObject("dhprice"), SvcPriceVO.class);
        settingDao.getSettingRep().saveAndFlush(setting);
        svcPriceDao.getPriceRep().saveAndFlush(twprice);
        svcPriceDao.getPriceRep().saveAndFlush(dhprice);
        return ResultUtils.success("操作成功");
    }
}
