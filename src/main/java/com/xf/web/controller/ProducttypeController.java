package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Producttype;
import com.xf.query.ProducttypeQuery;
import com.xf.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */

@Controller
@RequestMapping("/producttype")
public class ProducttypeController extends BaseController {

    @Autowired
    private IProducttypeService producttypeService;


    @RequestMapping("/index")
    public String index(){
        return "producttype/producttype";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Producttype> page(ProducttypeQuery query){
        Page page = producttypeService.findPageByQuery(query);
        return new UIPage(page);
    }



    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editProducttype")
    public Producttype beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Producttype dbProducttype = producttypeService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            return  dbProducttype;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Producttype producttype){
        try {
            producttypeService.save(producttype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editProducttype")Producttype producttype){
        try {
            producttypeService.save(producttype);
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
            producttypeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
