package vn.fis.logfile.vinasoy.controller.WEB.menu1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fis.logfile.vinasoy.ResponseMessage.ResponseProcess;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSProService;
import vn.fis.logfile.vinasoy.service.SuggestionService;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.security.Principal;
import java.util.List;


@Controller

@RequestMapping("/web/attachment")

public class AttachmentWEBController {
    @Autowired
    SuggestionService suggestionService;
    @Autowired
    I_AttachmentManagerSProService attachmentSProService;
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;

    //List 7 tài liệu truy cập gần nhất
    //List thư mục process
    //http://localhost:8080/web/attachment/menu1

    @GetMapping(value ={"/menu1","/"} )
    public String menu1(Model model, Principal principal) {
        String userName = principal.getName();
        List<SuggestionSproDto> suggestionSproDtos = suggestionService.findAll(userName);
        List<Process_SPro_Dto> process_sPro_dtos = attachmentSProService.findAllprocess(userName);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        model.addAttribute("FL", suggestionSproDtos);
        model.addAttribute("listProcess", process_sPro_dtos);
        return "index-1";
    }
    //List thư mục ticket
    @GetMapping(value ={"/menu1/{idProcess}"} )
    public String ticket( Model model,Principal principal, @PathVariable (name = "idProcess") Long idProcess) {
        String userName = principal.getName();
        List<Ticket_SPro_Dto> ticket_sPro_dtos = attachmentSProService.findAllticket(userName,idProcess);
        Process_SPro_Dto dto = attachmentSProService.findByIdProcess(userName,idProcess);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        model.addAttribute("nameProcess",dto);
        model.addAttribute("listTicket", ticket_sPro_dtos);
        return "index-1-1";
    }
    //List attachemnt
    @GetMapping(value ={"/menu1/{idProcess}/{idTicket}"} )
    public String attachment( Model model,Principal principal,
                              @PathVariable (name = "idProcess") Long idProcess,
                              @PathVariable (name = "idTicket") Long idTicket) {
        String userName = principal.getName();
        List<Attachment_SPro_Dto> attachment_sPro_dtos = attachmentSProService.findAll(idTicket);
        Ticket_SPro_Dto dto = attachmentSProService.findByIdTicket(idTicket,idProcess,userName);
        Process_SPro_Dto dto2 = attachmentSProService.findByIdProcess(userName,idProcess);
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        model.addAttribute("nameProcess",dto2);
        model.addAttribute("nameTicket",dto);
        model.addAttribute("listAttachment", attachment_sPro_dtos);
        return "index-1-1-1";
    }





}
