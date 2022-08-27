package vn.fis.logfile.vinasoy.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PDFUtils {

    public static byte[] readPDFReturnByte(String path) throws IOException {
        PdfReader reader = new PdfReader(path);
        int numberOfPages = reader.getNumberOfPages();
        String content = "";
        for (int i = 1; i <= numberOfPages; i++) {
            content += reader.getPageContent(i);
        }
        reader.close();
        return content.getBytes();
    }

    public static String readPDFReturnString(String path) throws IOException {
        PdfReader reader = new PdfReader(path);
        int numberOfPages = reader.getNumberOfPages();
        String content = "";
        for (int i = 1; i <= numberOfPages; i++) {
            content += PdfTextExtractor.getTextFromPage(reader, i);
        }
        reader.close();
        return content;
    }

    public static void createPDF(String path, String content) {
        // Tạo đối tượng tài liệu
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            File f = new File(path);
            String oldContent = "";
            if (!f.exists()) { //  ton tai
                oldContent = readPDFReturnString(path);
            }
            // Tạo đối tượng PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream(path));
            String newContent = oldContent + content;
            // Mở file để thực hiện ghi
            document.open();

            // Thêm nội dung sử dụng add function

            //Tạo 1 đoạn văn bản
            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingAfter(15);
            //Tạo 1 link
            Anchor anchor = new Anchor(newContent);
            anchor.setReference("http://sourceforge.net/projects/itext/");
            //Thêm link vào đoạn văn bản
            paragraph.add(anchor);
            //Thêm đoạn văn bản vào document
            document.add(paragraph);


            // Đóng File
            document.close();


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createCommentPDF(String pathPDF, String pathTXT) {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        File fdf = new File(pathPDF);
        try {
            File txt = new File(pathTXT);
            Scanner sc = new Scanner(txt);
            String content = "";

            PdfWriter.getInstance(document, new FileOutputStream(pathPDF));
            document.open();
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                System.out.println(temp);
                content += temp + "\n";
                //Tạo 1 đoạn văn bản
                Paragraph paragraph = new Paragraph();
                paragraph.setSpacingAfter(15);
                //Tạo 1 link
                Anchor anchor = new Anchor(temp);
                anchor.setReference(temp);
                //Thêm link vào đoạn văn bản
                paragraph.add(anchor);
                //Thêm đoạn văn bản vào document
                document.add(paragraph);
            }
            System.out.println(content);
            document.close();
            sc.close();
            txt.delete();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

}
