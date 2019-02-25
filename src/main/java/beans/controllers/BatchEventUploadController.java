package beans.controllers;

import beans.models.Event;
import beans.services.EventService;
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
public class BatchEventUploadController {

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;


    @RequestMapping(path = "/event/upload-from-json", method = RequestMethod.GET)
    public ModelAndView openUploadEventsFromJsonpage() {

        ModelAndView mv = new ModelAndView("event-upload-from-json");
        mv.addObject("message", "");
        return mv;
    }

    @RequestMapping(path = "/event/upload-from-json", method = RequestMethod.POST)
    public ModelAndView createEventsFromJson(@RequestParam("events_upload") MultipartFile file) {

        try {

            System.out.println("creating events from json file");

            ModelAndView mv = new ModelAndView("event-upload-from-json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            JsonParser parser = new JsonFactory().createParser(file.getBytes());

            Event[] events = mapper.readValue(parser, Event[].class);

            for (Event event : events) {
                eventService.create(event);
            }

            mv.addObject("message", "Events created.");

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
