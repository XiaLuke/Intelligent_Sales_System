package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Systemdictionarytype;
import com.xf.query.SystemdictionarytypeQuery;
import com.xf.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */

@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController extends BaseController {

    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;


    @RequestMapping("/index")
    public String index(){
        return "systemdictionarytype/systemdictionarytype";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Systemdictionarytype> page(SystemdictionarytypeQuery query){
        Page page = systemdictionarytypeService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editSystemdictionarytype")
    public Systemdictionarytype beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Systemdictionarytype dbSystemdictionarytype = systemdictionarytypeService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            return  dbSystemdictionarytype;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.save(systemdictionarytype);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSystemdictionarytype")Systemdictionarytype systemdictionarytype){
        try {
            systemdictionarytypeService.save(systemdictionarytype);
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
            systemdictionarytypeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
