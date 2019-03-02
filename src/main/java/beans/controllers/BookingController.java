package beans.controllers;

import beans.enums.Role;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(path = "/find-ticket-price", method = RequestMethod.GET)
    public ModelAndView openCalcTicketPricePage() {

        ModelAndView mv = new ModelAndView("booking-find-ticket-price");

        List<Event> allEvents = eventService.getAll();

        System.out.println("numb of events = " + allEvents.size());

        mv.addObject("events", allEvents);

        return mv;
    }

    @RequestMapping(path = "/calc-ticket-price", method = RequestMethod.GET)
    @ResponseBody
    public Map calcTicketPrice(@RequestParam("event_name") String eventName,
                               @RequestParam("vip_seat") String vipSeatNumber,
                               @RequestParam("common_seat") String commonSeatNumber) {

        System.out.println("calculating price of tickets v = " + vipSeatNumber + " and c = " + commonSeatNumber);

        Event event = eventService.getByName(eventName).get(0);

        User mockUser = new User("email", "name", LocalDate.now());
        Auditorium auditorium = event.getAuditorium();

        double vipTicketPrice = bookingService.getTicketPrice(
                eventName,
                auditorium.getName(),
                event.getDateTime(),
                Collections.singletonList(Integer.valueOf(vipSeatNumber)),
                mockUser
        );

        double commonTicketPrice = bookingService.getTicketPrice(
                eventName,
                auditorium.getName(),
                event.getDateTime(),
                Collections.singletonList(Integer.valueOf(commonSeatNumber)),
                mockUser
        );

        Map map = new HashMap();
        map.put("vip_ticket_price", vipTicketPrice);
        map.put("common_ticket_price", commonTicketPrice);

        return map;
    }

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public ModelAndView openBookingPage() {

        List<Event> allEvents = eventService.getAll();

        ModelAndView mv = new ModelAndView("booking");
        mv.addObject("events", allEvents);

        return mv;
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public void bookAnAuditorium(@RequestParam("user_id") long userId,
                                 @RequestParam("event_name") String eventName,
                                 @RequestParam("seat_number") int seat,
                                 HttpServletResponse response) {

        try {

            User user = userService.getById(userId);
            Event event = eventService.getByName(eventName).get(0);

            Auditorium auditorium = event.getAuditorium();

            double ticketPrice = bookingService.getTicketPrice(event.getName(),
                    auditorium.getName(),
                    event.getDateTime(),
                    Collections.singletonList(seat),
                    user);

            Ticket ticket = new Ticket(
                    event,
                    LocalDateTime.now(),
                    Collections.singletonList(seat),
                    user,
                    ticketPrice
            );

            bookingService.bookTicket(user, ticket);

            response.setStatus(HttpStatus.OK.value());

            System.out.println("a ticket booked successfully");

        } catch (Throwable e) {

            e.printStackTrace();
            System.err.println("not managed to book a ticket");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(path = "/find-booked-tickets", method = RequestMethod.GET)
    public ModelAndView openFindBookedTicketsPage() {

        System.out.println("open find book tickets page");

        List<Event> eventList = eventService.getAll();
        List<Auditorium> auditoriumList = auditoriumService.getAuditoriums();

        ModelAndView mv = new ModelAndView("booking-find-booked-tickets");
        mv.addObject("events", eventList);
        mv.addObject("auditoriums", auditoriumList);

        return mv;
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(path = "/search-booked-tickets", method = RequestMethod.GET)
    @ResponseBody
    public Map getBookedTickets(@RequestParam("event_name") String eventName,
                                @RequestParam("date_time") String dateTime,
                                @RequestParam("auditorium_name") String auditoriumName) {

        System.out.println("searching for booked tickets for a event = "+eventName);

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        List<Ticket> ticketsForEvent =
                bookingService.getTicketsForEvent(eventName, auditoriumName, localDateTime);

        Map map = new HashMap();
        map.put("tickets", ticketsForEvent);

        return map;
    }

}
