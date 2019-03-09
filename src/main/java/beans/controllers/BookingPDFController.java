package beans.controllers;

import beans.models.Ticket;
import beans.pdf.PdfTicketsReportView;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/find-booked-tickets-pdf")
public class BookingPDFController {

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/pdf")
    public ModelAndView getPDFWithBookedTickets(@RequestParam("event_name") String eventName,
                                                @RequestParam("date_time") String dateTime,
                                                @RequestParam("auditorium_name") String auditoriumName) {

        System.out.println("generating a pdf with booked tickets for "+ eventName + " event");

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        List<Ticket> bookedTickets =
                bookingService.getTicketsForEvent(eventName, auditoriumName, localDateTime);

        ModelAndView mv = new ModelAndView(new PdfTicketsReportView(), "tickets", bookedTickets);
        return mv;

    }

}
