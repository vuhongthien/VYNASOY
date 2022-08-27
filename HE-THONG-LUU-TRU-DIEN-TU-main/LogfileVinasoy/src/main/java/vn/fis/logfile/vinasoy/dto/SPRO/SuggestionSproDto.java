package vn.fis.logfile.vinasoy.dto.SPRO;




import java.time.LocalDateTime;
public class SuggestionSproDto {
    private String originalFileName;
    private LocalDateTime lastOpened;
    private Long idAttachment;

    public SuggestionSproDto(String originalFileName, LocalDateTime lastOpened, Long idAttachment) {
        this.originalFileName = originalFileName;
        this.lastOpened = lastOpened;
        this.idAttachment = idAttachment;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public LocalDateTime getLastOpened() {
        return lastOpened;
    }

    public void setLastOpened(LocalDateTime lastOpened) {
        this.lastOpened = lastOpened;
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }
}
