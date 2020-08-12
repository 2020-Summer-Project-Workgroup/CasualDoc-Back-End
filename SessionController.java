package com.sprint.summerproject.controllers;
import com.sprint.summerproject.models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionController {

    @Autowired
    private UsersService userservice;

    @RequestMapping("/tologin.action")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "login.action")
    public ModelAndView login(User users, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        users.setName(username);
        users.setPassword(password);
        users = this.userservice.dologin(users);
        if (users != null) {
            session.setAttribute("users", users);
            mav.setViewName("detail");
            return mav;
        } else {
            session.setAttribute("errormsg", "账号或密码错误!请重新输入");
        }
        mav.setViewName("login");
        return mav;
    }
}