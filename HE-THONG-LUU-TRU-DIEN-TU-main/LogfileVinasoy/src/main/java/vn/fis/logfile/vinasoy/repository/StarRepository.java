package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.model.Star;
@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

}
