package vn.fis.logfile.vinasoy.service;

import vn.fis.logfile.vinasoy.dto.SFLASH.Attachment_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Board_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.List_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Task_SFlash_Dto;

import java.util.List;

public interface I_Menu2Service {

    List<Attachment_SFlash_Dto> getSuggestion(Long idUser);

    List<Board_SFlash_Dto> getAllBoardManager();

    List<List_SFlash_Dto> getAllListByBoardManager(Long idBoard);

    List<Task_SFlash_Dto> getAllTaskByListAndBoardManager(Long idBoard, Long idList);

    List<Attachment_SFlash_Dto> getAllAttachmentByTaskListBoardManager(Long idBoard, Long idList, Long idTask);

    Board_SFlash_Dto findBoardById(Long idBoard);
    List_SFlash_Dto findListById(Long idList);
    Task_SFlash_Dto findTaskById(Long idTask);
}
