import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.IOException;

public class Main2 {


    public static void main(String[] args) throws IOException, DocumentException {

//        Document document1 = new Document();
//        ByteOutputStream byteOutputStream = new ByteOutputStream();
//        PdfWriter pdfWriter = PdfWriter.getInstance(document1, new FileOutputStream("iTextImageExample.pdf"));
//        document1.open();
//        Font font1 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//        document1.add(new Chunk("Hello World", font1));
//
//        document1.close();


//        String file = "file.pdf";
//        try (PDDocument doc = new PDDocument()) {
//
//
//            List<String> lines = new ArrayList<>();
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("22hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//            lines.add("hello");
//
//            while (!lines.isEmpty()) {
//                PDPage page = new PDPage();
//                populatePage(doc, page, lines);
//            }
//
//            doc.save(file);
//            System.out.println(file + " created!");
//        }
    }

//    private static void populatePage(PDDocument doc, PDPage page, List<String> lines) throws IOException {
//
//        doc.addPage(page);
//        PDFont font = PDFontFactory.createDefaultFont();
//        try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
//            contents.setFont(font, 14);
//            contents.beginText();
//            final int PARAGRAPH = 50;
//            final int Y_STARTING_COORDINATE = 750;
//
//            contents.newLineAtOffset(PARAGRAPH, Y_STARTING_COORDINATE);
//
//            for (int i = 0; i < 23; i++) {
//
//                String removedLine = null;
//                try {
//                    removedLine = lines.remove(0);
//                } catch (IndexOutOfBoundsException e) {
//                    break;
//                }
//
//                contents.newLineAtOffset(0, -30);
//                contents.showText(removedLine);
//            }
//            contents.endText();
//        }
//    }
}