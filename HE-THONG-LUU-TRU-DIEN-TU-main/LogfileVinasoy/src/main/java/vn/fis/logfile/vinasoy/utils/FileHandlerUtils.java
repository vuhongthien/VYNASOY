package vn.fis.logfile.vinasoy.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileHandlerUtils {

    public static String regexRemoveSpecialCharacter(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "");  // xoa ky tu dac bieu
    }

    public static String getTypeOfFile(String serverFileName) {
        return serverFileName.substring(serverFileName.lastIndexOf("."));
    }

    public static String getOriginalFileNameFromServerFileName(String serverFileName) {
        String s1 = serverFileName.substring(serverFileName.indexOf('_') + 1);
        return s1.substring(s1.indexOf('_') + 1);
    }


    public static String createLinkAttachment(Long id) {
      //  UUID uuid = UUID.randomUUID();
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("web/attachment/read/" + id)
                .toUriString();
    }

    /**
     * Lưu ở server thì theo quy tác:  WF1 / ID_TICKET / ID_FILE
     * trên db thì id_file -> file name theo quy tắc
     */
    public static void saveFile(MultipartFile multipartFile, String pathSaveFile) {
        try {
            String encryptContent = Base64.getEncoder().encodeToString(multipartFile.getBytes());
            File file = new File(pathSaveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] decryptContent = Base64.getDecoder().decode(encryptContent);
            fileOutputStream.write(decryptContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createFolder(String pathDirectory) {
        // Path:            \VINASOY\WF1\idTicket
        try {
            File file = new File(pathDirectory);
            if (file.exists()) {
                return;
            }
            Path dir = Paths.get(pathDirectory);
            Files.createDirectories(dir);
        } catch (Exception e) {
        }
    }

    public static int getSTT_MAX(String pathDirectoryTicket) {
        // Path:            \VINASOY\WF1\idTicket
        File f = new File(pathDirectoryTicket);
        File[] filesList = f.listFiles(); // lấy tất cả file nằm trong folder
        int STT_MAX = filesList.length;

        return STT_MAX;
    }
//    public static Long getSizeOfFile(String path, Long totalSize){
//        Path filePath = Paths.get(path);
//        Long length =0L;
//
//        if(Files.isDirectory(filePath)){
//            File f = new File(path);
//            for (File file : f.listFiles()) {
//                if(file.getName() == "Comments"){
//                    break;
//                }
//                String pathSub = file.getPath();
//                size =getSizeOfFile(pathSub,totalSize);
//            }
//        }else{
//            File f = new File(path);
//            size = f.length();
//        }
//        totalSize+= size;
//        return totalSize;
//    }


    public static long getSizeOfFile(String path) {
        File folder = new File(path);
        if(folder.isFile()){
            return folder.length();
        }
        long length = 0;
        File[] listFileInFolder = folder.listFiles();
        for (File file : listFileInFolder) {
            if (file.isFile())
                length += file.length();
            else
                length += getSizeOfFile(file.getPath());
        }
        return length;
    }



    public static String getSizeFinal(Long size){
        Long KB = 1024L;
        Long MB = (1024*KB);
        if(size >= MB){
            return   (long)(Math.ceil( (double)size/MB)) + " MB";
        }
        if(size >= KB){
            return   (long)(Math.ceil( (double)size/KB)) + " kB";
        }
        return size+" B";
    }

//    public static int getSTT_MAX(String pathDirectoryTicket) {
//        // Path:            \VINASOY\WF1\idTicket
//        File f = new File(pathDirectoryTicket);
//        File[] filesList = f.listFiles(); // lấy tất cả file nằm trong folder
//        int STT_MAX = 0;
//        for (int i = 0; i < filesList.length; i++) {
//            String sttBySubstring = getSttBySubstring(filesList[i].getPath());
//            int STT = 0;
//            try {
//                STT = Integer.parseInt(sttBySubstring);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (STT_MAX < STT) {
//                STT_MAX = STT;
//            }
//        }
//        return STT_MAX;
//    }

    public static String getSttBySubstring(String pathFile) {
        String kq = "-1";
        if (pathFile.contains("_") && pathFile.contains(".")) {
            File f = new File(pathFile);
            String name = f.getName();
            try {
                int indexFirst = name.lastIndexOf("_");
                int indexLast = name.lastIndexOf(".");
                if (indexFirst != -1 && indexLast != -1) {
                    kq = name.substring(indexFirst + 1, indexLast);
                }
            } catch (Exception e) {
                kq = "-1";
            }

        }
        return kq;

    }

    public static void main(String[] args) {
        String idEmployee = "NV@@123@!#";
        String idTask = "123@!#";
        String originalFileName = "True file name";


    }
}
