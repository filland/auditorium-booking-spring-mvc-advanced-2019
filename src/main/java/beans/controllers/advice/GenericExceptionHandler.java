package beans.controllers.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView handleException(Exception e){

        e.printStackTrace();

        ModelAndView mv = new ModelAndView("error");

        return mv;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView handleAccessDenied(){

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error_message", "You are not have rights to see this page.");

        return mv;
    }
}
