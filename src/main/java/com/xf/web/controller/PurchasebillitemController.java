package com.xf.web.controller;

import com.xf.domain.vo.ItemChartVo;
import com.xf.domain.vo.PurchaseBillItemVo;
import com.xf.query.PurchasebillitemQuery;
import com.xf.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController extends BaseController {

    @Autowired
    private IPurchasebillitemService purchasebillitemService;


    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/purchasebillitem";
    }


    @RequestMapping("/findItems")
    @ResponseBody
    public List<PurchaseBillItemVo> page(PurchasebillitemQuery query){
        return purchasebillitemService.findItems(query);
    }
    @RequestMapping("/findCharts")
    @ResponseBody
    public List<ItemChartVo> findCharts(PurchasebillitemQuery query){
        return purchasebillitemService.findCharts(query);
    }


}
