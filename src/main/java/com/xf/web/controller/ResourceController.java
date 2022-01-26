package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Resource;
import com.xf.query.ResourceQuery;
import com.xf.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;


    @RequestMapping("/index")
    public String index(){
        return "resource/resource";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Resource> page(ResourceQuery query){
        Page page = resourceService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editResource")
    public Resource beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Resource dbResource = resourceService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            return  dbResource;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Resource resource){
        try {
            resourceService.save(resource);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editResource")Resource resource){
        try {
            resourceService.save(resource);
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
            resourceService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
