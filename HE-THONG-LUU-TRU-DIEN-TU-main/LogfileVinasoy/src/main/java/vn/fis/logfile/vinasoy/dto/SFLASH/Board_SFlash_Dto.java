package vn.fis.logfile.vinasoy.dto.SFLASH;

import java.time.LocalDateTime;

public class Board_SFlash_Dto {
    private Long idBoard;
    private String boardName;
    private LocalDateTime boardCreatedAt;

    private String size;

    public Board_SFlash_Dto(Long idBoard, String boardName, LocalDateTime boardCreatedAt) {
        this.idBoard = idBoard;
        this.boardName = boardName;
        this.boardCreatedAt = boardCreatedAt;
    }

    public Board_SFlash_Dto(Long idBoard, String boardName, LocalDateTime boardCreatedAt, String size) {
        this.idBoard = idBoard;
        this.boardName = boardName;
        this.boardCreatedAt = boardCreatedAt;
        this.size = size;
    }

    public Board_SFlash_Dto() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
