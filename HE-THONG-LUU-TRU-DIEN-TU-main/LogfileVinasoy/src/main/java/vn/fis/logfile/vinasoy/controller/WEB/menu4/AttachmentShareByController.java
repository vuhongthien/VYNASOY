package vn.fis.logfile.vinasoy.controller.WEB.menu4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Share;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSProService;
import vn.fis.logfile.vinasoy.service.ShareService;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/web/attachment")
public class AttachmentShareByController {
    @Autowired
    ShareService shareService;
    @Autowired
    I_AttachmentManagerSProService attachmentSProService;
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;
    @GetMapping(value ={"/menu4"} )
    public String menu4(Model model, Principal principal) {
        String userName = principal.getName();
        List<Share> shareby = shareService.findAllsharebyme(userName);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("shareby", shareby);
        model.addAttribute("username", userName);
        return "index-4";
    }
    //List thư mục ticket
    @GetMapping(value ={"/menu4/{idProcess}"} )
    public String ticket( Model model,Principal principal, @PathVariable(name = "idProcess") Long idProcess) {
        String userName = principal.getName();
        List<Ticket_SPro_Dto> ticket_sPro_dtos = attachmentSProService.findAllticket(userName,idProcess);
        Process_SPro_Dto dto = attachmentSProService.findByIdProcess(userName,idProcess);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        model.addAttribute("nameProcess",dto);
        model.addAttribute("listTicket", ticket_sPro_dtos);
        return "index-4-1";
    }
    //List attachment
    @GetMapping(value ={"/menu4/ticket/{idTicket}"} )
    public String attachmentsharefrom( Model model,Principal principal,
                                       @PathVariable(name = "idTicket") Long idTicket) {
        String userName = principal.getName();
        List<Attachment_SPro_Dto> attachment_sPro_dtos = attachmentSProService.findAll(idTicket);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        model.addAttribute("listAttachment", attachment_sPro_dtos);
        return "index-4-1-1";
    }
}
