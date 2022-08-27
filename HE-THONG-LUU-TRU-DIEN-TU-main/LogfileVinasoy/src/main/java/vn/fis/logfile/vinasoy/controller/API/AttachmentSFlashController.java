package vn.fis.logfile.vinasoy.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSFlashService;

import java.time.LocalDateTime;
import java.util.List;

import static vn.fis.logfile.vinasoy.constant.Constant.*;

@RestController
@RequestMapping("/api/attachment/sflash")
public class AttachmentSFlashController {

    @Autowired
    I_AttachmentManagerSFlashService attachmentSFlashService;

//    @PostMapping(value = "/upload-close-task")
//    public List<Attachment> closeTask(@RequestParam("multipartFiles") MultipartFile[] multipartFiles,
//                                      @RequestParam("commentFiles") MultipartFile[] commentFiles,
//                                      @RequestParam("idEmployee") Long idEmployee,
//                                      @RequestParam("siteCode") String siteCode,
//                                      @RequestParam("departmentCode") String departmentCode,
//                                      @RequestParam("boardName") String boardName,
//                                      @RequestParam("idTask") String idTask,
//                                      @RequestParam("subject") String subject
//    ) {
//        return attachmentSFlashService.closeTask(multipartFiles, commentFiles, idEmployee, siteCode, departmentCode,
//                LocalDateTime.now(), boardName, idTask, subject);
//    }


    @PostMapping(value = "/save-one-attachment")
    public Attachment saveOneAttachmentInTask(@RequestParam("multipartFile") MultipartFile multipartFile,
                                              @RequestParam("idOwner") Long idOwner,
                                              @RequestParam("username") String username,
                                              @RequestParam("siteCode") String siteCode,
                                              @RequestParam("departmentCode") String departmentCode,
                                              @RequestParam("idTask") Long idTask,
                                              @RequestParam("taskName") String taskName,
                                              @RequestParam("idList") Long idList,
                                              @RequestParam("listName") String listName,
                                              @RequestParam("idBoard") Long idBoard,
                                              @RequestParam("boardName") String boardName
    ) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime taskCreatedAt = TIME_TASK;
        LocalDateTime listCreatedAt = TIME_LIST;
        LocalDateTime boardCreatedAt = TIME_BOARD;
        return attachmentSFlashService.saveOneAttachmentInTask(multipartFile, idOwner, username, createdAt, siteCode,
                departmentCode, idTask, taskName, taskCreatedAt, idList, listName, listCreatedAt,
                idBoard, boardName, boardCreatedAt);

    }

    @PostMapping(value = "/save-one-comment-attachment")
    public Attachment saveOneAttachmentInComment(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                 @RequestParam("idOwner") Long idOwner,
                                                 @RequestParam("username") String username,
                                                 @RequestParam("siteCode") String siteCode,
                                                 @RequestParam("departmentCode") String departmentCode,
                                                 @RequestParam("idTask") Long idTask,
                                                 @RequestParam("taskName") String taskName,
                                                 @RequestParam("idList") Long idList,
                                                 @RequestParam("listName") String listName,
                                                 @RequestParam("idBoard") Long idBoard,
                                                 @RequestParam("boardName") String boardName
    ) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime taskCreatedAt = TIME_TASK;
        LocalDateTime listCreatedAt = TIME_LIST;
        LocalDateTime boardCreatedAt = TIME_BOARD;
        Attachment attachment=attachmentSFlashService.saveCommentFileTXT(idOwner, username, createdAt, siteCode,
                departmentCode, idTask, taskName, taskCreatedAt, idList, listName, listCreatedAt,
                idBoard, boardName, boardCreatedAt);
        return attachmentSFlashService.saveOneAttachmentInCommentTask(multipartFile, idOwner, username, createdAt, siteCode,
                departmentCode, idTask, taskName, taskCreatedAt, idList, listName, listCreatedAt,
                idBoard, boardName, boardCreatedAt);

    }

    @PostMapping(value = "/save-all-comment-attachment")
    public Attachment saveAllComment( @RequestParam("idOwner") Long idOwner,
                                      @RequestParam("username") String username,
                                      @RequestParam("siteCode") String siteCode,
                                      @RequestParam("departmentCode") String departmentCode,
                                      @RequestParam("idTask") Long idTask,
                                      @RequestParam("taskName") String taskName,
                                      @RequestParam("idList") Long idList,
                                      @RequestParam("listName") String listName,
                                      @RequestParam("idBoard") Long idBoard,
                                      @RequestParam("boardName") String boardName
    ) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime taskCreatedAt = TIME_TASK;
        LocalDateTime listCreatedAt = TIME_LIST;
        LocalDateTime boardCreatedAt = TIME_BOARD;
        return attachmentSFlashService.saveAllComment(idOwner, username, createdAt, siteCode,
                departmentCode, idTask, taskName, taskCreatedAt, idList, listName, listCreatedAt,
                idBoard, boardName, boardCreatedAt);

    }
}
