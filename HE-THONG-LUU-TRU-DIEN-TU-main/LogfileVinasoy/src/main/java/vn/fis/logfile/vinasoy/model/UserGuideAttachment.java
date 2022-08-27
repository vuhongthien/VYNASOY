package vn.fis.logfile.vinasoy.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_GUIDE_ATTACHMENT")
public class UserGuideAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME_ATTACHMENT")
    private String name;
    @Column(name = "CREATE_AT")
    private LocalDateTime dateTime;
    @Column(name = "SIZE")
    private Long size;

    public UserGuideAttachment(Long id, String name, LocalDateTime dateTime, Long size) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.size = size;
    }

    public UserGuideAttachment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
