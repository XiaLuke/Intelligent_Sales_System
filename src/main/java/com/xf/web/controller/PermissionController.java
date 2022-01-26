package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Permission;
import com.xf.query.PermissionQuery;
import com.xf.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/index")
    public String index(){
        return "permission/permission";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Permission> page(PermissionQuery query){
        Page page = permissionService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editPermission")
    public Permission beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Permission dbPermission = permissionService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            return  dbPermission;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Permission permission){
        try {
            permissionService.save(permission);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPermission")Permission permission){
        try {
            permissionService.save(permission);
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
            permissionService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
