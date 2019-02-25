package beans.view;

import beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfTicketsReportView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {


        response.setHeader("Content-Disposition", "attachment; filename=\"tickets_list.pdf\"");

        @SuppressWarnings("unchecked")
        List<Ticket> list = (List<Ticket>) model.get("tickets");

        Table table = new Table(5);
        table.addCell("Event");
        table.addCell("User name");
        table.addCell("User email");
        table.addCell("Seat(s)");
        table.addCell("Price");

        for(Ticket ticket : list){
            table.addCell(ticket.getEvent().getName());
            table.addCell(ticket.getUser().getName());
            table.addCell(ticket.getUser().getEmail());
            table.addCell(ticket.getSeats());
            table.addCell(String.valueOf(ticket.getPrice()));
        }

        document.add(table);

    }
}
