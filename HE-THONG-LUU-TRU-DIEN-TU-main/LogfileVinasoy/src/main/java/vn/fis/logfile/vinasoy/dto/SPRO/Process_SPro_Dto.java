package vn.fis.logfile.vinasoy.dto.SPRO;

import java.time.LocalDateTime;


public class Process_SPro_Dto {

    private Long idProcess; // 15: Mã quy trình 2 con số
    private String processName;
    private LocalDateTime processCreatedAt;
    private String username;


    public Process_SPro_Dto(Long idProcess, String processName, LocalDateTime processCreatedAt, String username) {
        this.idProcess = idProcess;
        this.processName = processName;
        this.processCreatedAt = processCreatedAt;
        this.username = username;

    }

    public Process_SPro_Dto() {
    }

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public LocalDateTime getProcessCreatedAt() {
        return processCreatedAt;
    }

    public void setProcessCreatedAt(LocalDateTime processCreatedAt) {
        this.processCreatedAt = processCreatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
