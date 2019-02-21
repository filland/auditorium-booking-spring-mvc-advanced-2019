package beans.controllers;

import beans.models.Ticket;
import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.UUID;

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
        mv.addObject("message", "This is a message from backend!");

        return mv;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(required = false) User person) {

        System.out.println("creating a new user");

        String userName = UUID.randomUUID().toString().substring(0, 10);
        User user1 = new User();
        user1.setName(userName);
        user1.setEmail( userName + "@email.org");
        user1.setBirthday(LocalDate.now());

        User createdUser = userService.register(user1);

        System.out.println("user created with id " + createdUser.getId());

        System.out.println(userService.getUserByEmail(createdUser.getEmail()));

        ModelAndView mv = new ModelAndView("redirect:/registration");

        return mv;
    }
}
