package beans.controllers;

import beans.models.Ticket;
import beans.services.BookingService;
import beans.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/find-booked-tickets-pdf")
public class BookingPDFController {

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @Autowired
    private PdfService pdfService;

    @RequestMapping(method = RequestMethod.GET,headers = "Accept=application/pdf")
    @SuppressWarnings("Duplicates")
    public ResponseEntity<byte[]> getPDFWithBookedTickets(HttpServletResponse response,
                                                          @RequestParam("event_name") String eventName,
                                                          @RequestParam("date_time") String dateTime,
                                                          @RequestParam("auditorium_name") String auditoriumName) {

        System.out.println("creating pdf file with booked tickets");

        System.out.println(dateTime);

        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        List<Ticket> bookedTickets =
                bookingService.getTicketsForEvent(eventName, auditoriumName, localDateTime);

        System.out.println("bookedTickets size = "+ bookedTickets.size());

        response.addHeader("Content-Type", "application/pdf;charset=UTF-8");
        try {
            byte[] pdfFromTickets = pdfService.createPdfFromTickets(bookedTickets);

            return new ResponseEntity<>(pdfFromTickets, HttpStatus.OK);

        } catch (IOException e) {

            System.err.println("not managed to create pdf file with booked tickets");
            e.printStackTrace();

            return null;
        }



    }

}
