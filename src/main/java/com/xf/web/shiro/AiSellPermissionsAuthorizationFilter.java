package com.xf.web.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限，解决Ajax的问题
 *      你得告诉shiro(现在spring在管理),使用咱们的自定义过滤器
 */
public class AiSellPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //判断是不是Ajax请求，如果是,返回 {success:false,msg:"权限不够，请充值..."}
            //拿到Http的请求和响应
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse)response;
            //Ajax请求会发送:X-Requested-With,值是XMLHttpRequest
            // System.out.println(req.getHeader("X-Requested-With"));
            if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
                //设置响应头
                resp.setContentType("application/json;charset=UTF-8");
                //代表这里是Ajax请求 ,响应 {success:false,msg:"权限不够，请充值..."}
                response.getWriter().print("{\"success\":false,\"msg\":\"权限不够\"}");
            }else {
                //拿到失败的跳转路径,如果有这个路径就会跳转
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }

        return false;
    }
}
