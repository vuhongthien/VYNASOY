package vn.fis.logfile.vinasoy.controller.WEB.menu6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.security.Principal;

@Controller
@RequestMapping("/web/attachment")
public class CheckFileWEBController {
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;
    @GetMapping(value ={"/menu6"} )
    public String menu5(Model model, Principal principal) {
        String userName = principal.getName();
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        return "index-6";
    }
}
