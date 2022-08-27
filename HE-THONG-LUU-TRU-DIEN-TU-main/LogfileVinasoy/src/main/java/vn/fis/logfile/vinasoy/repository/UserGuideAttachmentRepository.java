package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
@Repository
public interface UserGuideAttachmentRepository extends JpaRepository<UserGuideAttachment, Long> {
    @Query(value = "SELECT * " +
            "FROM user_guide_attachment " +
            "WHERE CREATE_AT = (SELECT MAX(CREATE_AT) FROM user_guide_attachment)", nativeQuery = true)
    UserGuideAttachment findone();
}
