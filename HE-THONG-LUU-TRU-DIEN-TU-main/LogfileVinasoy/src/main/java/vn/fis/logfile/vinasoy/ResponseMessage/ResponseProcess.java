package vn.fis.logfile.vinasoy.ResponseMessage;

import java.time.LocalDateTime;

public class ResponseProcess {
    private Long idProcess; // 15: Mã quy trình 2 con số
    private String processName;
    private LocalDateTime processCreatedAt;
    private String username;
    private double size;


    public ResponseProcess(Long idProcess, String processName, LocalDateTime processCreatedAt, String username, double size) {
        this.idProcess = idProcess;
        this.processName = processName;
        this.processCreatedAt = processCreatedAt;
        this.username = username;
        this.size = size;
    }
}
