package vn.fis.logfile.vinasoy.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.annotations.PdfTextWebLink;
import com.spire.pdf.graphics.PdfBrushes;
import com.spire.pdf.graphics.PdfStringFormat;
import com.spire.pdf.graphics.PdfTrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class FilePDFUtils {
    public static void addMoreContentpdf(String pathComment, String linkoffile, String namefile) {
        try {
            PdfTrueTypeFont header = new PdfTrueTypeFont(new Font("Arial Unicode MS",Font.PLAIN,20),true);
            float y = 30;
            float x = 0;

                PdfDocument doc = new PdfDocument();
                    PdfPageBase page = doc.getPages().add();
                    //initialize x, y coordinates
                    //create a underlined font
                    PdfTrueTypeFont plainFont = new PdfTrueTypeFont(new Font("Arial Unicode MS",Font.PLAIN,13),true);
                    //add a hyper text link to pdf
                    PdfTextWebLink webLink = new PdfTextWebLink();
                    webLink.setText(namefile+"\n");
                    webLink.setUrl(linkoffile);
                    webLink.setFont(plainFont);
                    webLink.setBrush(PdfBrushes.getBlue());
                    webLink.drawTextWebLink(page.getCanvas(), new Point2D.Float(x, y));
                    doc.saveToFile(pathComment);
                    doc.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
