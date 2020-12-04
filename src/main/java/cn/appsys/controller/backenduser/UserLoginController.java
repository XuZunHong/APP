package cn.appsys.controller.backenduser;

import cn.appsys.pojo.BackendUser;
import cn.appsys.service.backenduser.BackendUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: APP
 * @description: 后台管理员登录控制层
 * @author: Xiaoxu
 * @create: 2020-11-26 17:19
 **/

@Controller
@RequestMapping({"/manager"})
public class UserLoginController {
    @Resource
    private BackendUserService backendUserService;

    @RequestMapping({"/login"})
    public String login() {
        return "backendlogin";
    }

    @RequestMapping(
            value = {"/dologin"},
            method = {RequestMethod.POST}
    )
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session) {
        BackendUser user = null;

        try {
            user = this.backendUserService.login(userCode, userPassword);
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (user != null) {
            session.setAttribute("userSession", user);
            return "redirect:/manager/backend/main";
        } else {
            request.setAttribute("error", "用户名或密码不正确");
            return "backendlogin";
        }
    }
    @RequestMapping({"/backend/main"})
    public String main(HttpSession session) {
        return session.getAttribute("userSession") == null ? "redirect:/manager/login" : "backend/main";
    }
    @RequestMapping({"/logout"})
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        return "backendlogin";
    }

}