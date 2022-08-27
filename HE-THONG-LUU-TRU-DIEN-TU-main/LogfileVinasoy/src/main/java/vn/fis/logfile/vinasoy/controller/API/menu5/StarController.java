package vn.fis.logfile.vinasoy.controller.API.menu5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Star;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSProService;
import vn.fis.logfile.vinasoy.service.StarService;

@RestController
@RequestMapping("/api/star/spro")
public class StarController {
    @Autowired
    StarService starService;
    @Autowired
    I_AttachmentManagerSProService i_attachmentManagerSProService;
    @GetMapping("/starattachment")
    public Star addstarattachment(@RequestParam("idAttachment") Long idAttachment) {
        Attachment_SPro_Dto attachment_sPro_dto = i_attachmentManagerSProService.findByIdAttachment(idAttachment);
        Star star = new Star();
        star.setCreatedAt(attachment_sPro_dto.getCreatedAt());
        star.setFromUser(attachment_sPro_dto.getUsername());
        star.setIdAttachment(attachment_sPro_dto.getId());
        star.setTypeFile(Long.valueOf(1));
        star.setNameFile(attachment_sPro_dto.getOriginalFileName());
        return starService.addstar(star);
    }
    @GetMapping("/starticket")
    public Star addstarticket(@RequestParam("idTicket") Long idTicket,
                              @RequestParam ("u") String u,
                              @RequestParam("idpro") Long idpro) {
        Ticket_SPro_Dto ticket_sPro_dto = i_attachmentManagerSProService.findByIdTicket(idTicket,idpro,u);
        Star star = new Star();
        star.setCreatedAt(ticket_sPro_dto.getTicketCreatedAt());
        star.setFromUser(ticket_sPro_dto.getUsername());
        star.setIdTicket(ticket_sPro_dto.getIdTicket());
        star.setNameFile(ticket_sPro_dto.getTicketName());
        star.setTypeFile(Long.valueOf(2));
        return starService.addstar(star);
    }
    @GetMapping("/starprocess")
    public Star addstarprocess(@RequestParam ("u") String u,
                               @RequestParam("idpro") Long idpro) {
        Process_SPro_Dto process_sPro_dto = i_attachmentManagerSProService.findByIdProcess(u,idpro);
        Star star = new Star();
        star.setCreatedAt(process_sPro_dto.getProcessCreatedAt());
        star.setFromUser(process_sPro_dto.getUsername());
        star.setIdProcess(process_sPro_dto.getIdProcess());
        star.setNameFile(process_sPro_dto.getProcessName());
        star.setTypeFile(Long.valueOf(3));
        return starService.addstar(star);
    }

}
