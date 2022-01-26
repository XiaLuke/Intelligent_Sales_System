package com.xf.web.controller;

import java.math.BigDecimal;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Purchasebill;
import com.xf.domain.Purchasebillitem;
import com.xf.query.PurchasebillQuery;
import com.xf.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */
import java.util.List;

@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController extends BaseController {

    @Autowired
    private IPurchasebillService purchasebillService;


    @RequestMapping("/index")
    public String index(){
        return "purchasebill/purchasebill";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Purchasebill> page(PurchasebillQuery query){
        Page page = purchasebillService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editPurchasebill")
    public Purchasebill beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Purchasebill dbPurchasebill = purchasebillService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            dbPurchasebill.setSupplier(null);
            dbPurchasebill.setBuyer(null);
            dbPurchasebill.getItems().clear();
            return  dbPurchasebill;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Purchasebill purchasebill){
        return saveOrUpdate(purchasebill);
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchasebill")Purchasebill purchasebill){
           return saveOrUpdate(purchasebill);
    }

    public JsonResult saveOrUpdate(Purchasebill purchasebill){
        //拿到订单的所有明细
        List<Purchasebillitem> items = purchasebill.getItems();
        //1.准备总数量与总金额
        BigDecimal totalNum = new BigDecimal("0");
        BigDecimal totalAmount = new BigDecimal("0");
        for (Purchasebillitem item : items) {
            //确定明细对应的订单
            item.setBill(purchasebill);
            //计算当前数据的小计  multiply:乘
            BigDecimal amount = item.getPrice().multiply(item.getNum());
            item.setAmount(amount);
            //2.计算总数量与总金额
            totalNum = totalNum.add(item.getNum());
            totalAmount = totalAmount.add(item.getAmount());
        }
        //3.设置总数量与总金额
        purchasebill.setTotalNum(totalNum);
        purchasebill.setTotalAmount(totalAmount);
        try {
            purchasebillService.save(purchasebill);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    /**
     * 删除功能,前台要求返回{success:true/false,msg:xxx}
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            purchasebillService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
