package vn.fis.logfile.vinasoy.controller.API.guide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

@RestController
@RequestMapping("/api/attachment")
public class UserGuideAttachmentController {
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;
        @GetMapping("/Guide")
    public UserGuideAttachment findAll() {
        return userGuideAttachmentService.findone();

    }
    @GetMapping("/Guide/{id}")
    public UserGuideAttachment findid(@RequestParam("id") Long id) {
        return userGuideAttachmentService.findByid(id);

    }
    @PostMapping("/GuideCreate")
    public UserGuideAttachment create(@RequestParam("multipartFile") MultipartFile multipartFile) {
        return userGuideAttachmentService.create(multipartFile);

    }
}
