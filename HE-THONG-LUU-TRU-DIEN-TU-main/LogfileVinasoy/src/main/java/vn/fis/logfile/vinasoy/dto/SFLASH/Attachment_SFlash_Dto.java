package vn.fis.logfile.vinasoy.dto.SFLASH;

import java.time.LocalDateTime;


public class Attachment_SFlash_Dto {
    private Long idAttachment;
    private String serverFileName;  // vd: NV01-TASK01-bao-cao
    private LocalDateTime createdAt;

    private String size;

    public Attachment_SFlash_Dto(Long idAttachment, String serverFileName, LocalDateTime createdAt) {
        this.idAttachment = idAttachment;
        this.serverFileName = serverFileName;
        this.createdAt = createdAt;
    }

    public Attachment_SFlash_Dto(Long idAttachment, String serverFileName, LocalDateTime createdAt, String size) {
        this.idAttachment = idAttachment;
        this.serverFileName = serverFileName;
        this.createdAt = createdAt;
        this.size = size;
    }

    public Attachment_SFlash_Dto() {
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
