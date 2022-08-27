package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.dto.SFLASH.Attachment_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Board_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.List_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Task_SFlash_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.util.List;

@Repository
public interface Menu2Repository extends JpaRepository<Attachment, Long> {

    @Query(value =
            "SELECT S.ID_ATTACHMENT, S.ID_USER, S.LAST_OPENED, A.SERVER_FILE_NAME " +
                    "FROM Suggestion S, Attachment A " +
                    "WHERE S.ID_ATTACHMENT = A.ID AND S.ID_USER = :idUser " +
                    "ORDER BY S.LAST_OPENED DESC " +
                    "LIMIT 7  ", nativeQuery = true)
    List<Object> getSuggestion(@Param("idUser") Long idUser);


    /************************       SFLASH      ********************/
    // ----------------------      Manager      -------------------//
    @Query(value = "SELECT * " +
                   "FROM  Attachment  " +
                   "WHERE  TYPE='TASK' OR TYPE = 'COMMENT-TASK' " , nativeQuery = true)
    List<Attachment> getAllAttachmentMenu2();

    @Query(nativeQuery = true)
    List<Board_SFlash_Dto> getAllBoardManager();

    @Query(nativeQuery = true)
    List<List_SFlash_Dto> getAllListByBoardManager(@Param("idBoard") Long idBoard);

    @Query(nativeQuery = true)
    List<Task_SFlash_Dto> getAllTaskByListAndBoardManager(@Param("idBoard") Long idBoard, @Param("idList") Long idList);

    @Query(nativeQuery = true)
    List<Attachment_SFlash_Dto> getAllAttachmentByTaskListBoardManager(@Param("idBoard") Long idBoard,
                                                                       @Param("idList") Long idList,
                                                                       @Param("idTask") Long idTask);

    @Query(nativeQuery = true)
    Board_SFlash_Dto findBoardById(@Param("idBoard") Long idBoard);

    @Query(nativeQuery = true)
    List_SFlash_Dto findListById(@Param("idList") Long idList);

    @Query(nativeQuery = true)
    Task_SFlash_Dto findTaskById(@Param("idTask") Long idTask);
}
