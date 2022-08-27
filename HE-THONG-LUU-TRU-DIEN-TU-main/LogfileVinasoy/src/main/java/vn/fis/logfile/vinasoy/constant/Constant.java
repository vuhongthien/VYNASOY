package vn.fis.logfile.vinasoy.constant;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Constant {

    // PATH_LOCAL : F:/VINASOY/
    public static final String PATH_LOCAL = "F:" + File.separator + "VINASOY" + File.separator;

    public static final LocalDateTime TIME_BOARD =  LocalDateTime.of(LocalDate.parse("2022-05-15"), LocalTime.parse("13:45:30"));
    public static final LocalDateTime TIME_LIST =  LocalDateTime.of(LocalDate.parse("2022-06-15"), LocalTime.parse("13:45:30"));
    public static final LocalDateTime TIME_TASK =  LocalDateTime.of(LocalDate.parse("2022-07-15"), LocalTime.parse("13:45:30"));

    public static final LocalDateTime TIME_PROCESS =  LocalDateTime.of(LocalDate.parse("2022-06-25"), LocalTime.parse("23:45:30"));
    public static final LocalDateTime TIME_TICKET =  LocalDateTime.of(LocalDate.parse("2022-07-25"), LocalTime.parse("23:45:30"));
}
