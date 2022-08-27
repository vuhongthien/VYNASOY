package vn.fis.logfile.vinasoy.dto.SFLASH;

import java.time.LocalDateTime;

public class List_SFlash_Dto {
    private Long idList;
    private String listName;
    private LocalDateTime listCreatedAt;
    private String size;

    public List_SFlash_Dto(Long idList, String listName, LocalDateTime listCreatedAt) {
        this.idList = idList;
        this.listName = listName;
        this.listCreatedAt = listCreatedAt;
    }

    public List_SFlash_Dto(Long idList, String listName, LocalDateTime listCreatedAt, String size) {
        this.idList = idList;
        this.listName = listName;
        this.listCreatedAt = listCreatedAt;
        this.size = size;
    }

    public List_SFlash_Dto() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
