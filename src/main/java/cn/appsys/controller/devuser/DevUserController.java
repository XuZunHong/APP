package cn.appsys.controller.devuser;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.devuser.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: APP
 * @description: 开发者控制类
 * @author: Xiaoxu
 * @create: 2020-11-26 23:33
 **/

@Controller
@RequestMapping({"/dev"})
public class DevUserController {
    @Autowired
    private DevUserService devUserService;
    @RequestMapping({"/login"})
    public String login() {
        return "devlogin";
    }

    @RequestMapping(
            value = {"/dologin"},
            method = {RequestMethod.POST}
    )
    public String doLogin(DevUser user,HttpServletRequest request, HttpSession session) {
        

        try {
            user = this.devUserService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user!=null) {
            session.setAttribute("devUserSession", user);
            return "redirect:/dev/flatform/main";
        } else {
            request.setAttribute("error", "用户名或密码不正确");
            return "devlogin";

        }
    }
    @RequestMapping({"/flatform/main"})
    public String main(HttpSession session) {
        return session.getAttribute("devUserSession") == null ? "redirect:/dev/login" : "developer/main";
    }

    @RequestMapping({"/logout"})
    public String logout(HttpSession session) {
        session.removeAttribute("devUserSession");
        return "devlogin";
    }
}