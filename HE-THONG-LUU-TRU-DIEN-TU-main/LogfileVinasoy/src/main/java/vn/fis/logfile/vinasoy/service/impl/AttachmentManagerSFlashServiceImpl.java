package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSFlashService;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static vn.fis.logfile.vinasoy.constant.Constant.PATH_LOCAL;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.*;
import static vn.fis.logfile.vinasoy.utils.FileNameUtils.createServerCommentFileNameSFLASHUtils;
import static vn.fis.logfile.vinasoy.utils.FileNameUtils.createServerFileNameSFlashUtils;
import static vn.fis.logfile.vinasoy.utils.FileWriterUtils.addMoreContent;
import static vn.fis.logfile.vinasoy.utils.PDFUtils.createCommentPDF;

@Service
public class AttachmentManagerSFlashServiceImpl implements I_AttachmentManagerSFlashService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Transactional
    public List<Attachment> closeTask(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                      Long idEmployee, String siteCode, String departmentCode,
                                      LocalDateTime taskCreatedAt, String boardName, String idTask,
                                      String subject) {
        return new ArrayList<>();
    }

    @Override
    public Attachment saveOneAttachmentInTask(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt,
                                              String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                              Long idList, String listName, LocalDateTime listCreatedAt,
                                              Long idBoard, String boardName, LocalDateTime boardCreatedAt) {
        createFolder(PATH_LOCAL + "WF2");
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard);
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList);
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask);

        Attachment attachmentTask = new Attachment();
        attachmentTask.setIdOwner(idOwner);
        attachmentTask.setUsername(username);
        attachmentTask.setOriginalFileName(multipartFile.getOriginalFilename());
        int STT_MAX = getSTT_MAX(PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList +
                File.separator + idTask);
        String customFileName = createServerFileNameSFlashUtils(siteCode, departmentCode, taskCreatedAt, boardName, idTask,
                taskName, String.valueOf(STT_MAX + 1));
        attachmentTask.setServerFileName(customFileName);
        attachmentTask.setCreatedAt(createdAt);


        attachmentTask.setSiteCode(siteCode);
        attachmentTask.setDepartmentCode(departmentCode);
        //String typeOfFile = getTypeOfFile(attachment.getOriginalFileName()); // lấy ra đuôi file: .txt, .docx, .pdf
        attachmentTask.setIdTask(idTask);
        attachmentTask.setTaskName(taskName);
        attachmentTask.setTaskCreatedAt(taskCreatedAt);
        attachmentTask.setIdList(idList);
        attachmentTask.setListName(listName);
        attachmentTask.setListCreatedAt(listCreatedAt);
        attachmentTask.setIdBoard(idBoard);
        attachmentTask.setBoardName(boardName);
        attachmentTask.setBoardCreatedAt(boardCreatedAt);

        attachmentTask.setStar(false);
        attachmentTask.setType("TASK");

        Attachment beginAttachmentComment = attachmentRepository.save(attachmentTask);
        Long idAttachmentComment = beginAttachmentComment.getId();
        String serverPath = "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask +
                File.separator + idAttachmentComment + ".pdf";
        beginAttachmentComment.setServerFilePath(serverPath);
        beginAttachmentComment.setHyperlinkAttachment(createLinkAttachment(idAttachmentComment));
        Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachmentComment);

        // save attachment in localhost/ server
        String localPath = PATH_LOCAL + serverPath;
        saveFile(multipartFile, localPath);

        // return for viewer
        return attachmentAfterCreate;
    }

    public Attachment saveCommentFileTXT(Long idOwner, String username, LocalDateTime createdAt,
                                         String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                         Long idList, String listName, LocalDateTime listCreatedAt,
                                         Long idBoard, String boardName, LocalDateTime boardCreatedAt) {
        // create a file with fileName: __comments
        // serverPath = CODEWF + File.separator + idTicket;
        Attachment attachmentListComment = new Attachment();
        attachmentListComment.setIdOwner(idOwner);
        attachmentListComment.setUsername(username);
        attachmentListComment.setOriginalFileName("CommentFiles in task " + idTask);
        String customFileName = createServerFileNameSFlashUtils(siteCode, departmentCode, taskCreatedAt, boardName, idTask,
                taskName, "Comment");
        attachmentListComment.setServerFileName(customFileName);
        attachmentListComment.setCreatedAt(LocalDateTime.now()); // TICKET / COMMENTS / TASK

        attachmentListComment.setSiteCode(siteCode);
        attachmentListComment.setDepartmentCode(departmentCode);
        //String typeOfFile = getTypeOfFile(attachmentListComment.getOriginalFileName()); // lấy ra đuôi file: .txt, .docx, .pdf
        attachmentListComment.setIdTask(idTask);
        attachmentListComment.setTaskName(taskName);
        attachmentListComment.setTaskCreatedAt(taskCreatedAt);
        attachmentListComment.setIdList(idList);
        attachmentListComment.setListName(listName);
        attachmentListComment.setListCreatedAt(listCreatedAt);
        attachmentListComment.setIdBoard(idBoard);
        attachmentListComment.setBoardName(boardName);
        attachmentListComment.setBoardCreatedAt(boardCreatedAt);

        attachmentListComment.setStar(false);
        attachmentListComment.setType("COMMENT-TASK");

        String serverPath = "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask +
                File.separator + "Comment.txt";
        attachmentListComment.setServerFilePath(serverPath);
        String localPath = PATH_LOCAL + serverPath;
        if (new File(localPath).exists()) {
            return attachmentListComment;
        }
        File commentList = new File(serverPath);
        return attachmentListComment;
    }

    public Attachment saveOneAttachmentInCommentTask(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt,
                                                     String siteCode, String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                                     Long idList, String listName, LocalDateTime listCreatedAt,
                                                     Long idBoard, String boardName, LocalDateTime boardCreatedAt) {
        createFolder(PATH_LOCAL + "WF2");
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard);
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList);
        createFolder(PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask);
        String pathFolderCommentTask = "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask + File.separator + "Comments";
        createFolder(PATH_LOCAL + pathFolderCommentTask);
        Attachment attachmentComment = new Attachment();
        attachmentComment.setIdOwner(idOwner);
        attachmentComment.setUsername(username);
        attachmentComment.setOriginalFileName(multipartFile.getOriginalFilename());
        int STT_MAX = getSTT_MAX(PATH_LOCAL + pathFolderCommentTask);
        String subject = taskName;
        String customFileName = createServerCommentFileNameSFLASHUtils(siteCode, departmentCode, taskCreatedAt,
                boardName, idTask, subject, String.valueOf(STT_MAX + 1));
        attachmentComment.setServerFileName(customFileName);
        attachmentComment.setCreatedAt(createdAt);

        attachmentComment.setSiteCode(siteCode);
        attachmentComment.setDepartmentCode(departmentCode);
        //String typeOfFile = getTypeOfFile(attachmentComment.getOriginalFileName()); // lấy ra đuôi file: .txt, .docx, .pdf
        attachmentComment.setIdTask(idTask);
        attachmentComment.setTaskName(taskName);
        attachmentComment.setTaskCreatedAt(taskCreatedAt);
        attachmentComment.setIdList(idList);
        attachmentComment.setListName(listName);
        attachmentComment.setListCreatedAt(listCreatedAt);
        attachmentComment.setIdBoard(idBoard);
        attachmentComment.setBoardName(boardName);
        attachmentComment.setBoardCreatedAt(boardCreatedAt);
        attachmentComment.setStar(false);
        attachmentComment.setType("COMMENT-IN-TASK");

        Attachment beginAttachment = attachmentRepository.save(attachmentComment);
        Long idAttachmentComment = beginAttachment.getId();
        String serverPath = pathFolderCommentTask + File.separator + idAttachmentComment + ".pdf";
        beginAttachment.setHyperlinkAttachment(createLinkAttachment(idAttachmentComment));
        beginAttachment.setServerFilePath(serverPath);
        Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachment);

        // save attachment in localhost/ server
        String localPathFileComment = PATH_LOCAL + serverPath;
        saveFile(multipartFile, localPathFileComment);

        String pathFileListComment = "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask + File.separator + "Comment.txt";
        String localPathFileListComment = PATH_LOCAL + pathFileListComment;
        //addMoreContent(localPathFileListComment, localPathFileComment);
        addMoreContent(localPathFileListComment, attachmentAfterCreate.getHyperlinkAttachment());
        // return for viewer
        return attachmentAfterCreate;
    }

    @Override
    public Attachment saveAllComment(Long idOwner, String username, LocalDateTime createdAt, String siteCode,
                                     String departmentCode, Long idTask, String taskName, LocalDateTime taskCreatedAt,
                                     Long idList, String listName, LocalDateTime listCreatedAt, Long idBoard,
                                     String boardName, LocalDateTime boardCreatedAt) {
        Attachment attachmentListComment = new Attachment();
        attachmentListComment.setIdOwner(idOwner);
        attachmentListComment.setUsername(username);
        attachmentListComment.setOriginalFileName("CommentFiles in task " + idTask);
        String customFileName = createServerFileNameSFlashUtils(siteCode, departmentCode, taskCreatedAt, boardName, idTask,
                taskName, "Comment");
        attachmentListComment.setServerFileName(customFileName);
        attachmentListComment.setCreatedAt(LocalDateTime.now()); // TICKET / COMMENTS / TASK

        attachmentListComment.setSiteCode(siteCode);
        attachmentListComment.setDepartmentCode(departmentCode);
        attachmentListComment.setIdTask(idTask);
        attachmentListComment.setTaskName(taskName);
        attachmentListComment.setTaskCreatedAt(taskCreatedAt);
        attachmentListComment.setIdList(idList);
        attachmentListComment.setListName(listName);
        attachmentListComment.setListCreatedAt(listCreatedAt);
        attachmentListComment.setIdBoard(idBoard);
        attachmentListComment.setBoardName(boardName);
        attachmentListComment.setBoardCreatedAt(boardCreatedAt);

        attachmentListComment.setStar(false);
        attachmentListComment.setType("COMMENT-TASK");

        String serverPath = "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask +
                File.separator + "Comment.pdf";
        attachmentListComment.setServerFilePath(serverPath);
        String localPath = PATH_LOCAL + serverPath;
        if (new File(localPath).exists()) {
            return attachmentListComment;
        }
        String fileCommentTXT = PATH_LOCAL + "WF2" + File.separator + idBoard + File.separator + idList + File.separator + idTask +
                File.separator + "Comment.txt";
        createCommentPDF(localPath, fileCommentTXT);
        Attachment attachmentAfterCreate = attachmentRepository.save(attachmentListComment);
        attachmentAfterCreate.setHyperlinkAttachment(createLinkAttachment(attachmentAfterCreate.getId()));
        Attachment attachmentFinal = attachmentRepository.save(attachmentAfterCreate);
        return attachmentFinal;
    }
}
