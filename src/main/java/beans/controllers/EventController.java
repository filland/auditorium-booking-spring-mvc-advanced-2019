package beans.controllers;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import beans.services.AuditoriumService;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @Autowired
    @Qualifier("auditoriumServiceImpl")
    private AuditoriumService auditoriumService;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateEventPage() {

        ModelAndView mv = new ModelAndView("event-create");

        mv.addObject("auditoriums", auditoriumService.getAuditoriums());

        return mv;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public void createEvent(@RequestParam("name") String name,
                            @RequestParam("rate") Rate rate,
                            @RequestParam("base_price") double basePrice,
                            @RequestParam("date_time") String dateTime,
                            @RequestParam("auditorium_name") String auditoriumName,
                            HttpServletResponse response) {

        try {

            Event event = new Event();
            event.setName(name);
            event.setRate(rate);
            event.setBasePrice(basePrice);
            event.setDateTime(LocalDateTime.parse(dateTime));

            Auditorium auditorium = auditoriumService.getByName(auditoriumName);
            event.setAuditorium(auditorium);

            System.out.println("creating a new event = " + event);

            Event createEvent = eventService.create(event);

            if (null != createEvent) {

                System.out.println("event created successfully");
                response.setStatus(HttpStatus.OK.value());

            } else {

                System.err.println("error while creating an event");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            }

        } catch (Throwable e) {

            System.err.println("error while creating an event");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @RequestMapping(path = "/create-events", method = RequestMethod.POST)
    @SuppressWarnings("Duplicates")
    @Deprecated
    public ModelAndView createSeveralEvents() {

        try {
            String auditoriumName = "Blue hall";
            Auditorium blueHall = auditoriumService.getByName(auditoriumName);
            Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
            Auditorium redHall = auditoriumService.getByName("Red hall");
            String eventName = "The revenant";
            LocalDateTime dateOfEvent = LocalDateTime.of(
                    LocalDate.of(2020, 2, 5),
                    LocalTime.of(15, 45, 0)
            );

            eventService.create(
                    new Event(
                            eventName,
                            Rate.HIGH,
                            60,
                            LocalDateTime.of(
                                    LocalDate.of(2020, 2, 5),
                                    LocalTime.of(9, 0, 0)
                            ),
                            blueHall)
            );
            eventService.create(
                    new Event(
                            "Some event",
                            Rate.HIGH,
                            60,
                            dateOfEvent,
                            blueHall
                    )
            );
            eventService.create(
                    new Event(
                            "TAT meeting",
                            Rate.HIGH,
                            60,
                            LocalDateTime.of(
                                    LocalDate.of(2020, 2, 5),
                                    LocalTime.of(21, 18, 0)
                            ),
                            blueHall)
            );

            Event event2 = new Event(
                    "Epam's day",
                    Rate.HIGH,
                    90,
                    LocalDateTime.of(
                            LocalDate.of(2020, 2, 5),
                            LocalTime.of(21, 18, 0)
                    ),
                    redHall);
            eventService.create(event2);

            Event event = new Event(
                    "BMTH concert",
                    Rate.HIGH,
                    150,
                    LocalDateTime.of(
                            LocalDate.of(2020, 2, 5),
                            LocalTime.of(21, 18, 0)),
                    yellowHall
            );
            eventService.create(event);

            return new ModelAndView("redirect:/");

        } catch (Throwable e) {

            System.out.println("error while creating a few events");
            return new ModelAndView("redirect:/");
        }
    }


}
