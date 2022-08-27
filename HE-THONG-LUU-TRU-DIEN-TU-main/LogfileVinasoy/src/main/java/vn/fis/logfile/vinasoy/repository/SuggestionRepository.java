package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.model.Suggestion;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {
    @Query( nativeQuery = true)
    List<SuggestionSproDto> suggestion(@Param("username") String username);

    @Query(value = "SELECT * " +
            "FROM suggestion " +
            "WHERE username = :username AND ID_ATTACHMENT = :idAttachment ", nativeQuery = true)
    Suggestion findidAttachmentofSuggestion(@Param("username") String username,
                                            @Param("idAttachment") Long idAttachment);

}
