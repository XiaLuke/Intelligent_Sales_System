package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UIPage;
import com.xf.domain.Dept;
import com.xf.query.DeptQuery;
import com.xf.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 */

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;


    @RequestMapping("/index")
    public String index(){
        return "dept/dept";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Dept> page(DeptQuery query){
        Page page = deptService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editDept")
    public Dept beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Dept dbDept = deptService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            return  dbDept;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Dept dept){
        try {
            deptService.save(dept);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editDept")Dept dept){
        try {
            deptService.save(dept);
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
            deptService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

}
