package cn.appsys.interceptor;

import cn.appsys.pojo.BackendUser;
import cn.appsys.pojo.DevUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: APP
 * @description: 系统拦截器类
 * @author: Xiaoxu
 * @create: 2020-11-26 13:19
 **/
public class SysInterceptor  extends HandlerInterceptorAdapter {
    public SysInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        BackendUser backendUser = (BackendUser)session.getAttribute("userSession");
        DevUser devUser = (DevUser)session.getAttribute("devUserSession");
        if (devUser != null) {
            return true;
        } else if (backendUser != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/403.jsp");
            return false;
        }
    }



    }
