package vn.fis.logfile.vinasoy.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FileNameUtils {
    /**
     * phần mềm quản lý và tự động hoá quy trình sau khi Ticket được Closed ---> S PRO
     * <p>
     * moduleCode: Mã phân hệ in hoa 2 ký tự
     * processCode: Mã quy trình 2 con số
     * ticketCreatedAt: Ngày tháng năm khởi tạo ticket
     * idTicket: lấy theo Ticket ID tương ứng được sinh ra trong hệ thống
     * subject: không cho phép 100 ký tự và ko có kí tự đặc biệt
     * ordinalNumber: số thứ tự file có trong các quy trình của workflow với 1 ticket
     **/
    public static String createServerFileNameSPROUtils(String moduleCode, Long processCode,
                                                       LocalDateTime ticketCreatedAt, Long idTicket,
                                                       String subject, String ordinalNumber) {
        String moduleCodeUpperCase = moduleCode.toUpperCase();
        String timeFormatted = formatDateTime(ticketCreatedAt);
        return moduleCodeUpperCase + processCode + "_" + timeFormatted + "_" + idTicket + "_" + subject + "_"
                + ordinalNumber;
    }

    public static String createServerCommentFileNameSPROUtils(String moduleCode, Long processCode,
                                                              LocalDateTime ticketCreatedAt, Long idTicket,
                                                              String subject, String ordinalNumber) {
        String moduleCodeUpperCase = moduleCode.toUpperCase();
        String timeFormatted = formatDateTime(ticketCreatedAt);
        return moduleCodeUpperCase + processCode + "_" + timeFormatted + "_" + idTicket + "_" + subject + "_"
                + "COMMENT_" + ordinalNumber;
    }


    /**
     * phần mềm giao việc tự động sau khi Task được Closed  ---> S FLASH
     * siteCode: mã site
     * departmentCode : mã phòng ban
     * taskCreatedAt :  Ngày tháng năm khởi tạo task
     * boardName: tên của board trong S Flash, trong board có thể có nhiều task
     * idTask: lấy theo Task ID tương ứng được sinh ra trong hệ thống
     * subject: không cho phép 100 ký tự và ko có kí tự đặc biệt.
     * ordinalNumber: số thứ tự file có trong 1 task
     **/
    public static String createServerFileNameSFlashUtils(String siteCode, String departmentCode,
                                                         LocalDateTime taskCreatedAt, String boardName, Long idTask,
                                                         String subject, String ordinalNumber) {
        String siteCodeUpperCase = siteCode.toUpperCase();
        String timeFormatted = formatDateTime(taskCreatedAt);
        return siteCodeUpperCase + "." + departmentCode + "_" + timeFormatted + "_" + boardName + "_" + idTask + "_" + subject + "_" + ordinalNumber;
    }

    public static String createServerCommentFileNameSFLASHUtils(String siteCode, String departmentCode,
                                                                LocalDateTime taskCreatedAt, String boardName, Long idTask,
                                                                String subject, String ordinalNumber) {
        String siteCodeUpperCase = siteCode.toUpperCase();
        String timeFormatted = formatDateTime(taskCreatedAt);
        return siteCodeUpperCase + "." + departmentCode + "_" + timeFormatted + "_" + boardName + "_" + idTask + "_" +
                subject + "_" + "COMMENT_" + ordinalNumber;

    }


    /****************************************************************************************
     *
     * custom
     *
     **/

    public static String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) {
        // AS15_07.07.2022_52_Subject_1
//        System.out.println(createServerFileNameSPROUtils("as", 15L, LocalDateTime.now(),
//                52L, "Subject", "1"));
//
//        // VND.PM_07.07.2022_Board_52_Subject_1
//        System.out.println(createServerFileNameSFlashUtils("vnd", 15L, LocalDateTime.now(),
//                "Board", "52", "Subject", "1"));
    }
}
