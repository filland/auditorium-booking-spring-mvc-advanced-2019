package beans.controllers;

import beans.daos.EventDAO;
import beans.models.Event;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(path = "/booking")
public class BookingController {

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @Autowired
    @Qualifier("auditoriumServiceImpl")
    private AuditoriumService auditoriumService;

    @Autowired
    private EventDAO eventDAO;

    @RequestMapping(path = "/find-ticket-price", method = RequestMethod.GET)
    public ModelAndView openCalcTicketPricePage(){

        ModelAndView mv = new ModelAndView("find-ticket-price");


        List<Event> allEvents = eventService.getAll();

        System.out.println("numb of events = " + allEvents.size());

        System.out.println("number of auditoriums = "+auditoriumService.getAuditoriums().size());

        Map<Long, List<Integer>> eventIdAndSeats = new HashMap<>();
        for (Event event : allEvents) {

            List<Integer> seats = IntStream
                    .of(event.getAuditorium().getSeatsNumber())
                    .boxed()
                    .collect(Collectors.toList());
            eventIdAndSeats.put(event.getId(), seats);
        }

        mv.addObject("events", allEvents);
//        mv.addObject("eventIdAndSeats", eventIdAndSeats);

        return mv;
    }

    @RequestMapping(path = "/calc-ticket-price", method = RequestMethod.GET)
    @ResponseBody
    public double calcTicketPrice(@RequestParam(required = false) String event,
                                  @RequestParam(required = false) String auditorium,
                                  @RequestParam(required = false) String dateTime,
                                  @RequestParam(required = false) String seats,
                                  @RequestParam(required = false) long userId){
        // but maybe some jquery ?
//        ModelAndView mv = new ModelAndView("ticket-price");
        return 99.99;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView openBookingPage(){

        ModelAndView mv = new ModelAndView("booking");

        return mv;
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public ModelAndView bookAnAuditorium(){

        // use javascript !
        // can we put REST and ModelAndView in one controller ?

        ModelAndView mv = new ModelAndView("booking-result");
        return mv;
    }

}
