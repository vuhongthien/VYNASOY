package vn.fis.logfile.vinasoy.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "STAR_ATTACHMENT")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ID_ATTACHMENT")
    private Long idAttachment;
    @Column(name = "ID_TICKET")
    private Long idTicket;
    @Column(name = "ID_PROCESS")
    private Long idProcess;
    @Column(name = "ID_TASK")
    private Long idTask;
    @Column(name = "ID_LIST")
    private Long idList;
    @Column(name = "ID_BOARD")
    private Long idBoard;
    @Column(name = "FROM_USER")
    private String fromUser;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "NAME_FILE")
    private String nameFile;
    @Column(name = "TYPE_FILE")
    private Long typeFile;

    public Star(Long id, Long idAttachment, Long idTicket, Long idProcess, Long idTask, Long idList, Long idBoard, String fromUser, LocalDateTime createdAt, String nameFile, Long typeFile) {
        this.id = id;
        this.idAttachment = idAttachment;
        this.idTicket = idTicket;
        this.idProcess = idProcess;
        this.idTask = idTask;
        this.idList = idList;
        this.idBoard = idBoard;
        this.fromUser = fromUser;
        this.createdAt = createdAt;
        this.nameFile = nameFile;
        this.typeFile = typeFile;
    }

    public Star() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public Long getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Long idProcess) {
        this.idProcess = idProcess;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }

    public Long getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(Long idBoard) {
        this.idBoard = idBoard;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Long getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(Long typeFile) {
        this.typeFile = typeFile;
    }
}
