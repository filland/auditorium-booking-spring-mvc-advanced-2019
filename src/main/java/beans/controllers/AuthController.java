package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout) {

        System.out.println("open login page");

        ModelAndView mv = new ModelAndView("login");

        if (null != error){
            mv.addObject("error", "Invalid username or/and password!");
        }

        if (null != logout) {
            mv.addObject("message", "You've been logged out successfully.");
        }

        return mv;
    }

}
