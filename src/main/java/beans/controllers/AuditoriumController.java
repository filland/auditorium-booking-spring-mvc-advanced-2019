package beans.controllers;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.CsvUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/auditorium")
public class AuditoriumController {

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @RequestMapping(path = "/seats", method = RequestMethod.GET)
    public @ResponseBody Map getSeats(@RequestParam("event_name") String eventName){

        System.out.println("fetching number of seats for event called = "+eventName);

        Event event = eventService.getByName(eventName).get(0);

        List<Integer> vipSeatsList = event.getAuditorium().getVipSeatsList();
        String vipSeats = CsvUtil.fromListToCsv(vipSeatsList);

        List<Integer> commonSeatsList = new ArrayList<>();
        for (int i = 1; i <= event.getAuditorium().getSeatsNumber(); i++) {
            if (!vipSeatsList.contains(i)){
                commonSeatsList.add(i);
            }
        }
        String commonSeats = CsvUtil.fromListToCsv(commonSeatsList);

        Map model = new HashMap();
        model.put("vip_seats", vipSeats);
        model.put("common_seats", commonSeats);

        return model;
    }

    @RequestMapping(path = "/seats-all", method = RequestMethod.GET)
    public @ResponseBody Map getAllSeats(@RequestParam("event_name") String eventName,
                                         HttpServletResponse response){

        System.out.println("fetching number of ALL seats");

        Event event = eventService.getByName(eventName).get(0);

        List<Integer> seatsList = new ArrayList<>();
        for (int i = 1; i <= event.getAuditorium().getSeatsNumber(); i++) {
            seatsList.add(i);
        }

        System.out.println("seats size === "+seatsList.size());

        Map model = new HashMap();
        model.put("seats", CsvUtil.fromListToCsv(seatsList));

        response.setStatus(HttpStatus.OK.value());

        return model;
    }

}
