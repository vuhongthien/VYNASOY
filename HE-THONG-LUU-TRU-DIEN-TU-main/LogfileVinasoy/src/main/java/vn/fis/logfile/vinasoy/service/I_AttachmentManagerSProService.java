package vn.fis.logfile.vinasoy.service;

import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface I_AttachmentManagerSProService {
    public List<Attachment> closeTicket(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                        Long idEmployee, String moduleCode, String processCode,
                                        LocalDateTime ticketCreatedAt, String idTicket, String subject);

//    public Attachment createCommentListSPro(Long idEmployee, String moduleCode, String processCode,
//                                            LocalDateTime ticketCreatedAt, String idTicket, String subject,
//                                            String type);
//
//    public List<AttachmentDTO> addMultipartFileSPro(MultipartFile[] multipartFiles, Long idEmployee, String moduleCode,
//                                                    String processCode, LocalDateTime ticketCreatedAt, String idTicket,
//                                                    String subject, String type, String pathServerOfCommentAttachment);

    public Attachment saveOneAttachmentInTicket(MultipartFile multipartFile, Long idOwner,String username, LocalDateTime createdAt, String moduleCode,
                                                  Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                                  String processName, LocalDateTime processCreatedAt) throws IOException;
    public Attachment saveComment(Long idOwner, String username, LocalDateTime createdAt, String moduleCode,
                                  Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                  String processName, LocalDateTime processCreatedAt);

    public Attachment saveOneAttachmentInCommentTicket(MultipartFile multipartFile, Long idOwner,String username, LocalDateTime createdAt, String moduleCode,
                                                    Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                                    String processName, LocalDateTime processCreatedAt);
    List<Process_SPro_Dto> findAllprocess(String u);
    List<Ticket_SPro_Dto> findAllticket(String u, Long id);
    Process_SPro_Dto findByIdProcess(String u,Long id);
    Ticket_SPro_Dto findByIdTicket(Long id,Long id2,String u);
    List<Attachment_SPro_Dto> findAll(Long idticket);
    Attachment_SPro_Dto findByIdAttachment(Long id);
}
