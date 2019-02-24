package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auditorium")
public class AuditoriumController {

    @RequestMapping(path = "/create-events", method = RequestMethod.GET)
    public ModelAndView createAuditoriumPage(){


        ModelAndView mv = new ModelAndView("auditorium-create");
        return mv;
    }

}
