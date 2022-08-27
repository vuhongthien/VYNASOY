package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.dto.SFLASH.*;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.repository.Menu2Repository;
import vn.fis.logfile.vinasoy.service.I_Menu2Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static vn.fis.logfile.vinasoy.constant.Constant.PATH_LOCAL;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.getSizeFinal;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.getSizeOfFile;

@Service
public class Menu2ServiceImpl implements I_Menu2Service {

    @Autowired
    Menu2Repository menu2Repository;



    @Override
    public List<Attachment_SFlash_Dto> getSuggestion(Long idUser) {
        int limit = 7;
        List<Object> list = menu2Repository.getSuggestion(idUser);
        // TO DO  : HOW TO MAP FROM OBJECT TO DTO ????
        return new ArrayList<>();
    }

    @Override
    public List<Board_SFlash_Dto> getAllBoardManager() {
        List<Board_SFlash_Dto> listAfterQuery = menu2Repository.getAllBoardManager();
        listAfterQuery.stream()
                .forEach(board -> {
                    String path = PATH_LOCAL + "WF2" + File.separator + board.getIdBoard();
                    board.setSize(getSizeFinal(getSizeOfFile(path)));
                });
        return listAfterQuery;
    }

    @Override
    public List<List_SFlash_Dto> getAllListByBoardManager(Long idBoard) {
        List<List_SFlash_Dto> listAfterQuery = menu2Repository.getAllListByBoardManager(idBoard);
        listAfterQuery.stream()
                .forEach(listTask -> {
                    String path = PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + listTask.getIdList();
                    listTask.setSize(getSizeFinal(getSizeOfFile(path)));
                });
        return listAfterQuery;
    }

    //    @Override
//    public List<Task_SFlash_Dto> getAllTaskByListAndBoardManager(Long idBoard, Long idList) {
//        return menu2Repository.getAllTaskByListAndBoardManager(idBoard, idList);
//    }
    @Override
    public List<Task_SFlash_Dto> getAllTaskByListAndBoardManager(Long idBoard, Long idList) {
        List<Task_SFlash_Dto> listAfterQuery = menu2Repository.getAllTaskByListAndBoardManager(idBoard, idList);
        listAfterQuery.stream()
                .forEach(task -> {
                    String path = PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList + File.separator + task.getIdTask();
                    task.setSize(getSizeFinal(getSizeOfFile(path)));
                });
        return listAfterQuery;
    }


    //    @Override
//    public List<Attachment_SFlash_Dto> getAllAttachmentByTaskListBoardManager(Long idBoard, Long idList, Long idTask) {
//        return menu2Repository.getAllAttachmentByTaskListBoardManager(idBoard,idList,idTask);
//    }
    @Override
    public List<Attachment_SFlash_Dto> getAllAttachmentByTaskListBoardManager(Long idBoard, Long idList, Long idTask) {
        List<Attachment> list = menu2Repository.getAllAttachmentMenu2();
        List<Attachment_SFlash_Dto> listSFlash = new ArrayList<>();
        list.stream()
                .filter(attachment ->
                        attachment.getIdBoard().equals(idBoard) && attachment.getIdList().equals(idList)
                                && attachment.getIdTask().equals(idTask))
                .collect(Collectors.toList())
                .forEach(attachment -> {
                    Attachment_SFlash_Dto a = new Attachment_SFlash_Dto();
                    a.setIdAttachment(attachment.getId());
                    a.setServerFileName(attachment.getServerFileName());
                    a.setCreatedAt(attachment.getCreatedAt());
                    a.setSize(getSizeFinal(getSizeOfFile(PATH_LOCAL + attachment.getServerFilePath())));
                    listSFlash.add(a);
                });
        return listSFlash;
    }


    /**
     * --------------------         Find one        ----------------------
     **/
    @Override
    public Board_SFlash_Dto findBoardById(Long idBoard) {
        return menu2Repository.findBoardById(idBoard);
    }

    @Override
    public List_SFlash_Dto findListById(Long idList) {
        return menu2Repository.findListById(idList);
    }

    @Override
    public Task_SFlash_Dto findTaskById(Long idTask) {
        return menu2Repository.findTaskById(idTask);
    }


}
