package vn.fis.logfile.vinasoy.dto.SPRO;

import java.time.LocalDateTime;

public class Attachment_SPro_Dto {
    private Long id;
    private String username;
    private String originalFileName;
    private String serverFilePath;
    private LocalDateTime createdAt;
    private String hyperlinkAttachment;
    private Long idTicket;
    private String ticketName;
    private LocalDateTime ticketCreatedAt;
    private Long idProcess;
    private String processName;
    private LocalDateTime processCreatedAt;
    private boolean star;
    private String type;

    public Attachment_SPro_Dto(Long id, String username, String originalFileName, String serverFilePath, LocalDateTime createdAt, String hyperlinkAttachment, Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess, String processName, LocalDateTime processCreatedAt, boolean star, String type) {
        this.id = id;
        this.username = username;
        this.originalFileName = originalFileName;
        this.serverFilePath = serverFilePath;
        this.createdAt = createdAt;
        this.hyperlinkAttachment = hyperlinkAttachment;
        this.idTicket = idTicket;
        this.ticketName = ticketName;
        this.ticketCreatedAt = ticketCreatedAt;
        this.idProcess = idProcess;
        this.processName = processName;
        this.processCreatedAt = processCreatedAt;
        this.star = star;
        this.type = type;
    }

    public Attachment_SPro_Dto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getHyperlinkAttachment() {
        return hyperlinkAttachment;
    }

    public void setHyperlinkAttachment(String hyperlinkAttachment) {
        this.hyperlinkAttachment = hyperlinkAttachment;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public LocalDateTime getTicketCreatedAt() {
        return ticketCreatedAt;
    }

    public void setTicketCreatedAt(LocalDateTime ticketCreatedAt) {
        this.ticketCreatedAt = ticketCreatedAt;
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

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
