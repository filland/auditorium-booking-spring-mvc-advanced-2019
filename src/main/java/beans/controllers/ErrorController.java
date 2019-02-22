package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public ModelAndView getErrorPage(@RequestParam("error_message") String errorMessage){

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error_message", errorMessage);

        return mv;
    }
}
