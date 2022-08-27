package vn.fis.logfile.vinasoy.model;

import lombok.Builder;
import lombok.ToString;
import vn.fis.logfile.vinasoy.dto.SFLASH.Attachment_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Board_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.List_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Task_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;

import javax.persistence.*;
import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;

@Entity
@ToString
@Builder
@Table(name = "ATTACHMENT")
@SqlResultSetMapping(name = "processdto", classes = {
        @ConstructorResult(targetClass = Process_SPro_Dto.class, columns = {
                @ColumnResult(name = "idProcess", type = Long.class),
                @ColumnResult(name = "processName", type = String.class),
                @ColumnResult(name = "processCreatedAt", type = LocalDateTime.class),
                @ColumnResult(name = "username", type = String.class),}) })
@NamedNativeQuery(name = "Attachment.listprocess",
        query ="SELECT distinct ID_PROCESS AS idProcess, PROCESS_NAME AS processName" +
                ", PROCESS_CREATED_AT AS processCreatedAt , USERNAME AS username " +
                "FROM attachment " +
                "WHERE username =:username",
        resultSetMapping = "processdto")
@NamedNativeQuery(name = "Attachment.findByidProcess",
        query ="SELECT distinct ID_PROCESS AS idProcess, PROCESS_NAME AS processName," +
                " PROCESS_CREATED_AT AS processCreatedAt ," +
                " USERNAME AS username FROM attachment where ID_PROCESS = :idProcess AND username =:username",
        resultSetMapping = "processdto")
@SqlResultSetMapping(name = "ticketdto", classes = {
        @ConstructorResult(targetClass = Ticket_SPro_Dto.class, columns = {
                @ColumnResult(name = "idTicket", type = Long.class),
                @ColumnResult(name = "ticketName", type = String.class),
                @ColumnResult(name = "ticketCreatedAt", type = LocalDateTime.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "processName", type = String.class),
                @ColumnResult(name = "idProcess", type = Long.class),}) })
@NamedNativeQuery(name = "Attachment.listticket",
        query ="SELECT distinct ID_TICKET AS idTicket, TICKET_NAME AS ticketName" +
                ", TICKET_CREATED_AT AS ticketCreatedAt , USERNAME AS username," +
                " PROCESS_NAME AS processName, ID_PROCESS AS idProcess " +
                "FROM attachment " +
                "WHERE username =:username AND ID_PROCESS = :idProcess",
        resultSetMapping = "ticketdto")
@NamedNativeQuery(name = "Attachment.findByidTicket",
        query ="SELECT distinct ID_TICKET AS idTicket, TICKET_NAME AS ticketName" +
                ", TICKET_CREATED_AT AS ticketCreatedAt , USERNAME AS username," +
                " PROCESS_NAME AS processName, ID_PROCESS AS idProcess " +
                "FROM attachment " +
                "WHERE  ID_TICKET = :idTicket AND ID_PROCESS = :idProcess AND username =:username",
        resultSetMapping = "ticketdto")



/*************************          SFLASH              *********************/
@SqlResultSetMapping(name = "boardDto", classes = {
        @ConstructorResult(targetClass = Board_SFlash_Dto.class, columns = {
                @ColumnResult(name = "idBoard", type = Long.class),
                @ColumnResult(name = "boardName", type = String.class),
                @ColumnResult(name = "boardCreatedAt", type = LocalDateTime.class),})})
@SqlResultSetMapping(name = "listDto", classes = {
        @ConstructorResult(targetClass = List_SFlash_Dto.class, columns = {
                @ColumnResult(name = "idList", type = Long.class),
                @ColumnResult(name = "listName", type = String.class),
                @ColumnResult(name = "listCreatedAt", type = LocalDateTime.class),})})
@SqlResultSetMapping(name = "taskDto", classes = {
        @ConstructorResult(targetClass = Task_SFlash_Dto.class, columns = {
                @ColumnResult(name = "idTask", type = Long.class),
                @ColumnResult(name = "taskName", type = String.class),
                @ColumnResult(name = "taskCreatedAt", type = LocalDateTime.class),})})
@SqlResultSetMapping(name = "attachmentDto", classes = {
        @ConstructorResult(targetClass = Attachment_SFlash_Dto.class, columns = {
                @ColumnResult(name = "idAttachment", type = Long.class),
                @ColumnResult(name = "serverFileName", type = String.class),
                @ColumnResult(name = "createdAt", type = LocalDateTime.class),})})

@NamedNativeQuery(name = "Attachment.getAllBoardManager",
        query = "SELECT DISTINCT ID_BOARD as idBoard , BOARD_NAME as boardName, BOARD_CREATED_AT as boardCreatedAt " +
                "FROM attachment " +
                "WHERE TYPE='TASK' OR TYPE = 'COMMENT-TASK'",
        resultSetMapping = "boardDto")

@NamedNativeQuery(name = "Attachment.getAllListByBoardManager",
        query = "SELECT DISTINCT ID_LIST as idList , LIST_NAME as listName, LIST_CREATED_AT as listCreatedAt " +
                "FROM attachment " +
                "WHERE ID_BOARD = :idBoard ",
        resultSetMapping = "listDto")

@NamedNativeQuery(name = "Attachment.getAllTaskByListAndBoardManager",
        query = "SELECT DISTINCT ID_TASK as idTask , TASK_NAME as taskName, TASK_CREATED_AT as taskCreatedAt " +
                "FROM attachment " +
                "WHERE ID_BOARD = :idBoard AND ID_LIST = :idList ",
        resultSetMapping = "taskDto")

@NamedNativeQuery(name = "Attachment.getAllAttachmentByTaskListBoardManager",
        query = "SELECT DISTINCT ID_ATTACHMENT as idAttachment , SERVER_FILE_NAME as serverFileName, CREATED_AT as createdAt " +
                "FROM attachment " +
                "WHERE ID_BOARD = :idBoard AND ID_LIST = :idList AND ID_TASK = :idTask ",
        resultSetMapping = "attachmentDto")

// -- find one
@NamedNativeQuery(name = "Attachment.findBoardById",
        query = "SELECT DISTINCT ID_BOARD as idBoard , BOARD_NAME as boardName, BOARD_CREATED_AT as boardCreatedAt " +
                "FROM attachment " +
                "WHERE ID_BOARD = :idBoard",
        resultSetMapping = "boardDto")

@NamedNativeQuery(name = "Attachment.findListById",
        query = "SELECT DISTINCT ID_LIST as idList , LIST_NAME as listName, LIST_CREATED_AT as listCreatedAt " +
                "FROM attachment " +
                "WHERE ID_LIST = :idList ",
        resultSetMapping = "listDto")

@NamedNativeQuery(name = "Attachment.findTaskById",
        query = "SELECT DISTINCT ID_TASK as idTask , TASK_NAME as taskName, TASK_CREATED_AT as taskCreatedAt " +
                "FROM attachment " +
                "WHERE ID_TASK = :idTask ",
        resultSetMapping = "taskDto")








public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATTACHMENT")
    private Long id;
    @Column(name = "ID_OWNER")
    private Long idOwner;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName; // vd: bao-cao
    @Column(name = "SERVER_FILE_NAME")
    private String serverFileName;  // vd: NV01-TASK01-bao-cao
    @Column(name = "SERVER_FILE_PATH")
    private String serverFilePath;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "HYPERLINK_ATTACHMENT")
    private String hyperlinkAttachment;

    /********************************** TICKET ******************************/
    @Column(name = "MODULE_CODE")
    private String moduleCode; // AD: Mã phân hệ in hoa 2 ký tự
    @Column(name = "ID_TICKET")
    private Long idTicket;
    @Column(name = "TICKET_NAME")
    private String ticketName;
    @Column(name = "TICKET_CREATED_AT")
    private LocalDateTime ticketCreatedAt;

    @Column(name = "ID_PROCESS")
    private Long idProcess; // 15: Mã quy trình 2 con số
    @Column(name = "PROCESS_NAME")
    private String processName;
    @Column(name = "PROCESS_CREATED_AT")
    private LocalDateTime processCreatedAt;

    /********************************** TASK ******************************/
    @Column(name = "SITE_CODE")
    private String siteCode; // VND: Mã site
    @Column(name = "DEPARTMENT_CODE")
    private String departmentCode; // PM: Mã phòng ban

    @Column(name = "ID_TASK")
    private Long idTask;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "TASK_CREATED_AT")
    private LocalDateTime taskCreatedAt;
    @Column(name = "ID_LIST")
    private Long idList;
    @Column(name = "LIST_NAME")
    private String listName;
    @Column(name = "LIST_CREATED_AT")
    private LocalDateTime listCreatedAt;
    @Column(name = "ID_BOARD")

    private Long idBoard;
    @Column(name = "BOARD_NAME")
    private String boardName;
    @Column(name = "BOARD_CREATED_AT")
    private LocalDateTime boardCreatedAt;
    @Column(name = "STAR")

    private boolean star;
    @Column(name = "TYPE")
    private String type; // TICKET / TICKET - COMMENT / TASK / TASK - COMMENT

    public Attachment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
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

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
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

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
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

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getTaskCreatedAt() {
        return taskCreatedAt;
    }

    public void setTaskCreatedAt(LocalDateTime taskCreatedAt) {
        this.taskCreatedAt = taskCreatedAt;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public LocalDateTime getListCreatedAt() {
        return listCreatedAt;
    }

    public void setListCreatedAt(LocalDateTime listCreatedAt) {
        this.listCreatedAt = listCreatedAt;
    }

    public Long getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(Long idBoard) {
        this.idBoard = idBoard;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public LocalDateTime getBoardCreatedAt() {
        return boardCreatedAt;
    }

    public void setBoardCreatedAt(LocalDateTime boardCreatedAt) {
        this.boardCreatedAt = boardCreatedAt;
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
