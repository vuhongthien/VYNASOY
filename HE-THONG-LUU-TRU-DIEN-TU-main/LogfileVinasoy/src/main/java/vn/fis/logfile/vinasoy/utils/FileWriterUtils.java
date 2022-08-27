package vn.fis.logfile.vinasoy.utils;

import java.io.*;

public class FileWriterUtils {

    public static void writeCommentFile(String pathComment, String contentLink) {
        try {
            FileWriter fw = new FileWriter(pathComment);
            fw.write(contentLink);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addMoreContent(String pathComment, String contentLink) {
        try {
            File file = new File(pathComment);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            /* Below three statements would add three
             * mentioned Strings to the file in new lines.
             */
            pw.println(contentLink);
            //This will add a new line to the file content
            pw.println("");
            pw.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }


    public static void main(String args[]) {
        try {
            String pathComment = "F:\\VINASOY\\WF2\\BoardName\\112\\list-file-comment.txt";
            String contentLink = "Welcome to java.";
            //writeCommentFile(pathComment, contentLink);
            addMoreContent(pathComment,contentLink+"add21");
            System.out.println("Success...");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
