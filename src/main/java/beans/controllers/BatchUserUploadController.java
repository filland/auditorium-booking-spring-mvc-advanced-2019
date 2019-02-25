package beans.controllers;

import beans.models.User;
import beans.services.UserService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BatchUserUploadController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(path = "/user/upload-from-json", method = RequestMethod.GET)
    public ModelAndView openUploadusersFromJsonpage() {

        ModelAndView mv = new ModelAndView("user-upload-from-json");
        mv.addObject("message", "");
        return mv;
    }

    @RequestMapping(path = "/user/upload-from-json", method = RequestMethod.POST)
    public ModelAndView createUsersFromJson(@RequestParam("users_upload") MultipartFile file) {

        try {

            System.out.println("creating users from json file");

            ModelAndView mv = new ModelAndView("user-upload-from-json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            JsonParser parser = new JsonFactory().createParser(file.getBytes());

            User[] users = mapper.readValue(parser, User[].class);

            for (User user : users) {

                userService.register(user);
            }

            mv.addObject("message", "Users created.");

            return mv;

        } catch (JsonParseException e1) {
            e1.printStackTrace();
            return new ModelAndView("redirect:/");
        } catch (JsonMappingException e1) {
            e1.printStackTrace();
            return new ModelAndView("redirect:/");
        } catch (IOException e1) {
            e1.printStackTrace();
            return new ModelAndView("redirect:/");
        }

    }

}
