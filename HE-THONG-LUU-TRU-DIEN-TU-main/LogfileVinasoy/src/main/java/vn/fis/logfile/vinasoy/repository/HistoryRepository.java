package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
}
