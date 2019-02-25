package beans.controllers;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Enumeration;

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

            System.out.println("user = "+ user);

//            Enumeration<String> requestHeaderNames = request.getHeaderNames();
//
//            while (requestHeaderNames.hasMoreElements()){
//
//                String headerName = requestHeaderNames.nextElement();
//                System.out.println(headerName+ " = "+ request.getHeader(headerName));
//            }

            System.out.println("trying to create a new user");

//            LocalDate date = LocalDate.parse(birthday);
//
//            User newUser = new User();
//            newUser.setName(name);
//            newUser.setEmail(email);
//            newUser.setBirthday(date);

//            User createdUser = userService.register(newUser);
            User createdUser = userService.register(user);

            System.out.println("user created with id " + createdUser.getId());

            System.out.println(userService.getUserByEmail(createdUser.getEmail()));

            ModelAndView mv = new ModelAndView("redirect:/");
            return mv;

        } catch (Throwable e) {

            ModelAndView mv = new ModelAndView("error");
            mv.addObject("error_message", "Invalid data provided.");

            return mv;
        }

    }
}
