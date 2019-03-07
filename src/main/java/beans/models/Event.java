package beans.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:42 PM
 */
public class Event {

    private long          id;
    private String        name;
    private Rate          rate;
    private double        basePrice;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    private Auditorium    auditorium;

    private double ticketPrice;

    public Event() {
    }

    public Event(String name, Rate rate, double basePrice, LocalDateTime dateTime, Auditorium auditorium) {
        this(-1, name, rate, basePrice, dateTime, auditorium);
    }

    public Event(long id, String name, Rate rate, double basePrice, LocalDateTime dateTime, Auditorium auditorium) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.basePrice = basePrice;
        this.dateTime = dateTime;
        this.auditorium = auditorium;
    }

    public Event(long id, String name, Rate rate, double basePrice, LocalDateTime dateTime, Auditorium auditorium, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.basePrice = basePrice;
        this.dateTime = dateTime;
        this.auditorium = auditorium;
        this.ticketPrice = ticketPrice;
    }

    public Event withId(Long eventId) {
        return new Event(eventId, this.name, this.rate, this.basePrice, this.dateTime, this.auditorium);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                Double.compare(event.basePrice, basePrice) == 0 &&
                Double.compare(event.ticketPrice, ticketPrice) == 0 &&
                Objects.equals(name, event.name) &&
                rate == event.rate &&
                Objects.equals(dateTime, event.dateTime) &&
                Objects.equals(auditorium, event.auditorium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rate, basePrice, dateTime, auditorium, ticketPrice);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", basePrice=" + basePrice +
                ", dateTime=" + dateTime +
                ", auditorium=" + auditorium +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
