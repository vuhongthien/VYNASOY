package vn.fis.logfile.vinasoy.model;

import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SUGGESTION")
@SqlResultSetMapping(name = "Suggestiondto", classes = {
        @ConstructorResult(targetClass = SuggestionSproDto.class, columns = {
                @ColumnResult(name = "originalFileName", type = String.class),
                @ColumnResult(name = "lastOpened", type = LocalDateTime.class),
                @ColumnResult(name = "idAttachment", type = Long.class)}) })
        @NamedNativeQuery(name = "Suggestion.suggestion",
        query ="SELECT B.ORIGINAL_FILE_NAME AS originalFileName ," +
                " A.LAST_OPENED AS lastOpened  , B.ID_ATTACHMENT AS idAttachment  " +
                " FROM suggestion A,attachment B " +
                " WHERE  A.ID_ATTACHMENT = B.ID_ATTACHMENT AND A.username = :username " +
                "ORDER BY A.LAST_OPENED Desc " +
                "Limit 7 ;", resultSetMapping = "Suggestiondto")

public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "ID_ATTACHMENT")
    private Long idAttachment;

    @Column(name = "LAST_OPENED")
    private LocalDateTime lastOpened;

    public Suggestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public LocalDateTime getLastOpened() {
        return lastOpened;
    }

    public void setLastOpened(LocalDateTime lastOpened) {
        this.lastOpened = lastOpened;
    }
}
