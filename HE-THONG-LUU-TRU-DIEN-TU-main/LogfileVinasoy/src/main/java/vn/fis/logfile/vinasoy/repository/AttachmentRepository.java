package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
//    @Query(value = "SELECT * FROM ttt.attachment where originalFileName like %:key% or attachmentDateTime like %:key% or idEmployee like %:key% ", nativeQuery = true)
//    List<Attachment> findByKey(@Param("key") String key);
//    @Query(value = "SELECT * FROM ttt.attachment where originalFileName like %:key% or attachmentDateTime like %:key% or idEmployee like %:key% ", nativeQuery = true)
//    List<Attachment> findByKey(@Param("key") String key);

@Query( nativeQuery = true)
List<Process_SPro_Dto> listprocess(@Param("username") String username);
@Query( nativeQuery = true)
List<Ticket_SPro_Dto> listticket(@Param("username") String username, @Param("idProcess") Long idProcess);
@Query( nativeQuery = true)
Process_SPro_Dto findByidProcess(@Param("idProcess") Long idProcess,@Param("username") String username);
    @Query( nativeQuery = true)
    Ticket_SPro_Dto findByidTicket(@Param("idTicket") Long idTicket,
                                   @Param("idProcess") Long idProcess,
                                   @Param("username") String username);
}