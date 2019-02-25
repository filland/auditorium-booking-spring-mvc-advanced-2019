package beans.services;

import beans.models.Ticket;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfService {

    public byte[] createPdfFromTickets(List<Ticket> tickets) throws IOException {

        try (PDDocument doc = new PDDocument()) {

            List<String> lines = tickets
                    .stream()
                    .map(ticket -> ticket.getUser().getName() + " " +
                            ticket.getUser().getEmail() + " " +
                            ticket.getSeats() + " " +
                            ticket.getPrice()).collect(Collectors.toList());

            while (!lines.isEmpty()) {
                PDPage page = new PDPage();
                populatePage(doc, page, lines);
            }

            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            doc.save(byteOutputStream);

            return byteOutputStream.toByteArray();
        }

    }

    private void populatePage(PDDocument doc, PDPage page, List<String> lines) throws IOException {

        doc.addPage(page);
        PDFont font = PDFontFactory.createDefaultFont();
        try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
            contents.setFont(font, 14);
            contents.beginText();
            final int PARAGRAPH = 50;
            final int Y_STARTING_COORDINATE = 750;

            contents.newLineAtOffset(PARAGRAPH, Y_STARTING_COORDINATE);

            for (int i = 0; i < 23; i++) {

                String removedLine = null;
                try {
                    removedLine = lines.remove(0);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }

                contents.newLineAtOffset(0, -30);
                contents.showText(removedLine);
            }
            contents.endText();
        }
    }
}
