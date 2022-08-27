package vn.fis.logfile.vinasoy.dto.SPRO;

import java.time.LocalDateTime;

public class Ticket_SPro_Dto {
    private Long idTicket;
    private String ticketName;
    private LocalDateTime ticketCreatedAt;
    private String username;
    private String processName;
    private Long idProcess;

    public Ticket_SPro_Dto(Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, String username, String processName, Long idProcess) {
        this.idTicket = idTicket;
        this.ticketName = ticketName;
        this.ticketCreatedAt = ticketCreatedAt;
        this.username = username;
        this.processName = processName;
        this.idProcess = idProcess;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }
}