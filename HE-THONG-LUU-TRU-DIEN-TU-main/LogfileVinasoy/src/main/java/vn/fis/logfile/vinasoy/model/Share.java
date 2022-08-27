package vn.fis.logfile.vinasoy.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SHARE_ATTACHMENT")
public class Share {
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
    @Column(name = "TO_USER")
    private String toUser;
    @Column(name = "PERMISSION")
    private String permission;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "CREATED_AT_SHARE")
    private LocalDateTime createdAtShare;
    @Column(name = "NAME_FILE_SHARE")
    private String nameFileShare;
    @Column(name = "TYPE_FILE")
    private Long typeFile;

    public Share() {
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

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAtShare() {
        return createdAtShare;
    }

    public void setCreatedAtShare(LocalDateTime createdAtShare) {
        this.createdAtShare = createdAtShare;
    }

    public String getNameFileShare() {
        return nameFileShare;
    }

    public void setNameFileShare(String nameFileShare) {
        this.nameFileShare = nameFileShare;
    }

    public Long getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(Long typeFile) {
        this.typeFile = typeFile;
    }
}
