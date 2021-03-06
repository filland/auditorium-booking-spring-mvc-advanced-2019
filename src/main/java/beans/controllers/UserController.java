package beans.controllers;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {

        System.out.println("registration page opened");

        ModelAndView mv = new ModelAndView("registration");

        mv.addObject("title", "Registration page");

        return mv;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute User user,
                                     HttpServletRequest request) {

        try {

            System.out.println("trying to create a new user");

            User createdUser = userService.register(user);

            System.out.println("user created with id " + createdUser.getId());
            System.out.println(createdUser);

            ModelAndView mv = new ModelAndView("redirect:/login");
            return mv;

        } catch (Throwable e) {

            e.printStackTrace();
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("error_message", "Invalid data provided.");

            return mv;
        }

    }
}

