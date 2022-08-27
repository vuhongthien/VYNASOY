package vn.fis.logfile.vinasoy.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.service.impl.AttachmentManagerSProServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static vn.fis.logfile.vinasoy.constant.Constant.*;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.createLinkAttachment;

@RestController
@RequestMapping("/api/attachment/spro")
public class AttachmentSProController {
    @Autowired
    AttachmentManagerSProServiceImpl attachmentSProService;

//    @PostMapping(value = "/upload-close-ticket")
//    public List<Attachment> closeTicket(@RequestParam("multipartFiles") MultipartFile[] multipartFiles,
//                                           @RequestParam("commentFiles") MultipartFile[] commentFiles,
//                                           @RequestParam("idEmployee") Long idEmployee,
//                                           @RequestParam("moduleCode") String moduleCode,
//                                           @RequestParam("processCode") String processCode,
//                                           @RequestParam("idTicket") String idTicket,
//                                           @RequestParam("subject") String subject
//    ) {
//        return attachmentSProService.closeTicket(multipartFiles, commentFiles, idEmployee, moduleCode, processCode,
//                LocalDateTime.now(), idTicket, subject);
//
//    }

    @PostMapping(value = "/save-one-attachment")
    public Attachment saveOneAttachmentInTicket(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                  @RequestParam("idOwner") Long idOwner,
                                                  @RequestParam("username") String username,
                                                  @RequestParam("moduleCode") String moduleCode,
                                                  @RequestParam("idTicket") Long idTicket,
                                                  @RequestParam("ticketName") String ticketName,
                                                  @RequestParam("idProcess") Long idProcess,
                                                  @RequestParam("processName") String processName
                                                    //@RequestParam("processCreatedAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime processCreatedAt,
                                                    //@RequestParam("ticketCreatedAt")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ticketCreatedAt
    ) throws IOException {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime processCreatedAt = TIME_PROCESS;
        LocalDateTime ticketCreatedAt = TIME_TICKET;
        return attachmentSProService.saveOneAttachmentInTicket(multipartFile, idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);

    }

    @PostMapping(value = "/save-one-comment-attachment")
    public Attachment saveOneAttachmentInComment(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                 @RequestParam("idOwner") Long idOwner,
                                                 @RequestParam("username") String username,
                                                 @RequestParam("moduleCode") String moduleCode,
                                                 @RequestParam("idTicket") Long idTicket,
                                                 @RequestParam("ticketName") String ticketName,
                                                 @RequestParam("idProcess") Long idProcess,
                                                 @RequestParam("processName") String processName
                                                // @RequestParam("processCreatedAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime processCreatedAt,
                                               //  @RequestParam("ticketCreatedAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ticketCreatedAt
    ) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime processCreatedAt = TIME_PROCESS;
        LocalDateTime ticketCreatedAt = TIME_TICKET;
        attachmentSProService.saveComment(idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);
        return attachmentSProService.saveOneAttachmentInCommentTicket(multipartFile, idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);

    }
}
