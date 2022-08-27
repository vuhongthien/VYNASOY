package vn.fis.logfile.vinasoy.service;

import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.time.LocalDateTime;
import java.util.List;

public interface I_AttachmentManagerSFlashService {

    public List<Attachment> closeTask(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                      Long idEmployee, String siteCode, String departmentCode,
                                      LocalDateTime taskCreatedAt, String boardName, String idTask,
                                      String subject);

    //    public List<AttachmentDTO> addMultipartFilesSFlash(MultipartFile[] multipartFiles, Long idEmployee,
//                                                       String siteCode, String departmentCode, LocalDateTime taskCreatedAt,
//                                                       String boardName, String idTask, String subject, String type,
//                                                       String serverPath);
//    public Attachment createCommentListSFlash(Long idEmployee, String moduleCode, String processCode,
//                                              LocalDateTime ticketCreatedAt,String boardName, String idTicket, String subject,
//                                              String type);
    public Attachment saveOneAttachmentInTask(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt,
                                                 String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                                 Long idList, String listName, LocalDateTime listCreatedAt,
                                                 Long idBoard, String boardName, LocalDateTime boardCreatedAt);

//    public Attachment saveCommentFileTXT(Long idOwner, String username, LocalDateTime createdAt,
//                                  String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
//                                  Long idList, String listName, LocalDateTime listCreatedAt,
//                                  Long idBoard, String boardName, LocalDateTime boardCreatedAt);

    public Attachment saveCommentFileTXT(Long idOwner, String username, LocalDateTime createdAt,
                                         String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                         Long idList, String listName, LocalDateTime listCreatedAt,
                                         Long idBoard, String boardName, LocalDateTime boardCreatedAt);
    public Attachment saveOneAttachmentInCommentTask(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt,
                                                        String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                                        Long idList, String listName, LocalDateTime listCreatedAt,
                                                        Long idBoard, String boardName, LocalDateTime boardCreatedAt);

    Attachment saveAllComment( Long idOwner, String username, LocalDateTime createdAt,
                              String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                              Long idList, String listName, LocalDateTime listCreatedAt,
                              Long idBoard, String boardName, LocalDateTime boardCreatedAt);
}
