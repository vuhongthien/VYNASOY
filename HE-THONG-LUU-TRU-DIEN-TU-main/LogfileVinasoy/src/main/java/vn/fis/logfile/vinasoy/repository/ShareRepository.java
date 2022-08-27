package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.model.Share;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share,Long> {
    @Query(value = "SELECT * " +
            "FROM SHARE_ATTACHMENT " +
            " WHERE TO_USER  =:forlogin", nativeQuery = true)
    List<Share> shareforme(@Param("forlogin") String forlogin);
    @Query(value = "SELECT * " +
            " FROM SHARE_ATTACHMENT " +
            " WHERE FROM_USER =:bylogin", nativeQuery = true)
    List<Share> sharebyme(@Param("bylogin") String bylogin);
}
